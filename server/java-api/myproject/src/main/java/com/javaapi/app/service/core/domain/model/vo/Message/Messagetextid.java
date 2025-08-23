package com.javaapi.app.service.core.domain.model.vo.Message;
 
import java.util.UUID;

public class Messagetextid{

    private final UUID id;

    public Messagetextid(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}