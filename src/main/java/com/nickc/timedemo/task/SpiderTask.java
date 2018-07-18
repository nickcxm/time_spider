package com.nickc.timedemo.task;

import com.nickc.timedemo.pojo.Rate;
import com.nickc.timedemo.service.RateService;
import com.nickc.timedemo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
@Component
public class SpiderTask {
    @Autowired
    Utils utils;
    @Autowired
    RateService rateService;

    @Scheduled(fixedRate = 10000)
    public void SpiderAndSave()throws Exception{
        for (int i = 1314; i < 1340; i++) {
            String a=utils.getTimeById(i);
            String[] args1=a.split(" ");
            if (a.length()>0&&a.charAt(0)!='-'){
                Rate rate=new Rate();
                rate.setId((long)i);
                rate.setName(args1[0]);
                rate.setTime(args1[1]+" "+args1[2]);
                rate.setRate(Double.parseDouble(args1[3]));
                rateService.save(rate);
            }
        }
    }
}
