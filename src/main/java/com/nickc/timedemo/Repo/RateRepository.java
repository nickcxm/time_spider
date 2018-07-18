package com.nickc.timedemo.Repo;

import com.nickc.timedemo.pojo.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
public interface RateRepository extends JpaRepository<Rate,Long>{
    List<Rate> findAllById (Long id);
}
