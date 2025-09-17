package com.javaapi.app.service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;
import com.javaapi.app.service.usecase.Conversation.ConversationUsecase;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationUsecase conversationUsecase;

    public ConversationController(ConversationUsecase conversationUsecase) {
        this.conversationUsecase = conversationUsecase;
    }


    //select page
    @GetMapping("/select")
    public List<SelectConvDTO> selectConversations(HttpServletRequest request) {
        String session = request.getHeader("cookie");
        return conversationUsecase.selectConversations(session);
    }


    //one to one page 
    @PostMapping("/create")
    public UUID createConversation(@RequestBody ConversationInDTO conversationInDTO) {
        return conversationUsecase.createConversation(conversationInDTO);
    }

    @GetMapping("/get/{roomID}")
    public List<ConversationOutDTO> getConversations(HttpServletRequest request,@PathVariable("roomID") UUID roomID) {
        String session = request.getHeader("cookie");
        return conversationUsecase.getConversations(session, roomID);
    }
}