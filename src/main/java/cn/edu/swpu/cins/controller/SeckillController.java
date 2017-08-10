package cn.edu.swpu.cins.controller;


import cn.edu.swpu.cins.dto.Exposer;
import cn.edu.swpu.cins.dto.SeckillExecution;
import cn.edu.swpu.cins.dto.SeckillResult;
import cn.edu.swpu.cins.entity.Seckill;
import cn.edu.swpu.cins.enums.SeckillStateEnum;
import cn.edu.swpu.cins.exception.RepeatKillException;
import cn.edu.swpu.cins.exception.SeckillCloseException;
import cn.edu.swpu.cins.exception.SeckillException;
import cn.edu.swpu.cins.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return  "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public SeckillResult<Exposer> exposer(Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀方法
     * @param seckillId  秒杀商品ID
     * @param userPhone  秒杀用户手机
     * @param md5        秒杀key
     * @return
     */
    @RequestMapping(value = "{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
                                                   @CookieValue(value = "userPhone",required = false)Long userPhone,
                                                   @PathVariable("md5")String md5){
        SeckillResult<SeckillExecution> result;
        SeckillExecution seckillExecution;
        if(userPhone == null){
            result = new SeckillResult<SeckillExecution>(false,"未注册");
        }else {
            try{
                // dao操作 seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                // procedure 操作
                seckillExecution = seckillService.executeSeckill(seckillId,userPhone,md5);
                result = new SeckillResult<SeckillExecution>(true,seckillExecution);
            }catch (SeckillCloseException e){
                seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
                result = new SeckillResult<SeckillExecution>(true,seckillExecution);
            }catch (RepeatKillException e){
                seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.REPEAT_KILL);
                result = new SeckillResult<SeckillExecution>(true,seckillExecution);
            }catch (SeckillException e){
                seckillExecution = new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
                result = new SeckillResult<SeckillExecution>(true,seckillExecution);
            }
        }
        return result;
    }


    @RequestMapping(value = "/time/now",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> execute(Model model) {
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
