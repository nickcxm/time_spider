package com.nickc.timedemo.pojo;

import org.python.antlr.ast.Str;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author chengxumin
 * @date 2018/7/18
 */
@Entity
public class Rate {
    @Id
    @GeneratedValue
    private long normalid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNormalid() {
        return normalid;
    }

    public void setNormalid(long normalid) {
        this.normalid = normalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
