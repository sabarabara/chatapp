package com.javaapi.app.service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;
import com.javaapi.app.service.usecase.Conversation.ConversationUsecase;


@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationUsecase conversationUsecase;

    public ConversationController(ConversationUsecase conversationUsecase) {
        this.conversationUsecase = conversationUsecase;
    }


    //select page 
    public List<SelectConvDTO> selectConversations(UUID roomid, String username) {
        return List.of(new SelectConvDTO(roomid, username)); // Placeholder for actual implementation
    }


    //one to one page 
    public UUID createConversation(@RequestBody ConversationInDTO conversationInDTO) {

        return conversationUsecase.createConversation(conversationInDTO);
    }

    public List<ConversationOutDTO> getConversations(UUID roomid, UUID userid) {
        return conversationUsecase.getConversations(roomid, userid);
    }
}