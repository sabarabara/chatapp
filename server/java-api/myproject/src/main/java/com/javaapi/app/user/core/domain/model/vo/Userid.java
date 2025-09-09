package com.javaapi.app.user.core.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Userid{

    private final String userid;


    public Userid(@JsonProperty("userid") String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
}