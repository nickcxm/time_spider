package com.nickc.timedemo.service.impl;

import com.nickc.timedemo.Repo.RateRepository;
import com.nickc.timedemo.pojo.Rate;
import com.nickc.timedemo.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
@Service
public class RateServiceImpl implements RateService{
    //比例数据库mapper
    @Autowired
    RateRepository repository;
    @Override
    public void save(Rate rate) {
        repository.save(rate);
    }

    @Override
    public Rate getLatestById(Long id) {
        List<Rate> rateList=repository.findAllById(id);
        if (rateList.get(0)==null){
            return null;
        }
        return rateList.get(rateList.size()-1);
    }
}
