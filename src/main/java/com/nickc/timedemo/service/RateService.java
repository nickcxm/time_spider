package com.nickc.timedemo.service;

import com.nickc.timedemo.pojo.Rate;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
public interface RateService {
    void save(Rate rate);
    Rate getLatestById(Long id);
}
