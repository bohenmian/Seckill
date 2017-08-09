package cn.edu.swpu.cins.service;

import cn.edu.swpu.cins.dto.Exposer;
import cn.edu.swpu.cins.dto.SeckillExecution;
import cn.edu.swpu.cins.entity.Seckill;
import cn.edu.swpu.cins.exception.RepeatKillException;
import cn.edu.swpu.cins.exception.SeckillCloseException;
import cn.edu.swpu.cins.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();


    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);


    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException
            , RepeatKillException, SeckillCloseException;

    /**
     * 存储过程执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * SeckillExecution
     *
     */
    SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5);
}
