package com.javaapi.app.user.core.domain.model.vo;

import java.util.UUID;


public class Userid{

    private final UUID userid;


    public Userid(String userid) {
        this.userid = UUID.fromString(userid);
    }

    public Userid(UUID userid) {
        this.userid = userid;
    }


    public UUID getUserid() {
        return userid;
    }
}