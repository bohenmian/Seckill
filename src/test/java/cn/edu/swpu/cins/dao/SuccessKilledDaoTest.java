package cn.edu.swpu.cins.dao;

import cn.edu.swpu.cins.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1000L;
        long phone = 18244294694L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.print("insertCount=" + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long phone = 18244294694L;
        SuccessKilled successKilledId = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.print(successKilledId);
        System.out.print(successKilledId.getSeckill());
    }

}