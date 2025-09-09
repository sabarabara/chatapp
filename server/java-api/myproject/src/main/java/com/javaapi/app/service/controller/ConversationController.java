package com.javaapi.app.service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.RoomidDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;
import com.javaapi.app.service.usecase.Conversation.ConversationUsecase;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationUsecase conversationUsecase;

    public ConversationController(ConversationUsecase conversationUsecase) {
        this.conversationUsecase = conversationUsecase;
    }


    //select page
    @GetMapping("/select")
    public List<SelectConvDTO> selectConversations(HttpSession session) {
        return conversationUsecase.selectConversations(session);
    }


    //one to one page 
    @PostMapping("/create")
    public UUID createConversation(@RequestBody ConversationInDTO conversationInDTO) {
        return conversationUsecase.createConversation(conversationInDTO);
    }

    @PostMapping("/get")
    public List<ConversationOutDTO> getConversations(HttpSession session ,@RequestBody RoomidDTO roomiddDto) {
        return conversationUsecase.getConversations(session, roomiddDto.getRoomid());
    }
}