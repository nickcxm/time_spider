package com.nickc.timedemo.controller;

import com.nickc.timedemo.pojo.Rate;
import com.nickc.timedemo.service.RateService;
import com.nickc.timedemo.utils.Utils;
import org.python.antlr.ast.Str;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
@RestController
@RequestMapping("/api/rate")
@EnableScheduling
public class timeSpiderController {
    @Autowired
    Utils utils;
    @Autowired
    RateService rateService;
    @RequestMapping(value = "/{tid}",method = RequestMethod.GET)
    public Rate getTimeById(@PathVariable long tid)throws Exception{
        Rate rate=new Rate();
        rate=rateService.getLatestById(tid);
        if (null!=rate){
            return rate;
        }
        return null;
    }

    @RequestMapping(value = "/{tid}",method = RequestMethod.POST)
    public Rate saveTimeById(@PathVariable long tid)throws Exception{
        String a=utils.getTimeById((int)tid);
        String[] args1=a.split(" ");
        if (a.charAt(0)!='-'){
            Rate rate=new Rate();
            rate.setId(tid);
            rate.setName(args1[0]);
            rate.setTime(args1[1]+" "+args1[2]);
            rate.setRate(Double.parseDouble(args1[3]));
            rateService.save(rate);
            return rate;
        }
        return null;
    }



}
