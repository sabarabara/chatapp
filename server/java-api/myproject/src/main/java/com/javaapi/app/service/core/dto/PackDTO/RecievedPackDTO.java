package com.javaapi.app.service.core.dto.PackDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecievedPackDTO {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("hobby")
    private String hobby;

    @JsonProperty("advice")
    private String advice;

    
    public RecievedPackDTO() {}

    public String getUserId() { return userId; }
    public String getHobby() { return hobby; }
    public String getAdvice() { return advice; }
}
