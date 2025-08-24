package com.javaapi.app.user.core.domain.model.vo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Userid{

    private final UUID userid;


    public Userid(@JsonProperty("userid") String userid) {
        this.userid = UUID.fromString(userid);
    }

    public Userid(UUID userid) {
        this.userid = userid;
    }


    public UUID getUserid() {
        return userid;
    }
}