package com.javaapi.app.service.usecase.Conversation;

import java.util.List;
import java.util.UUID;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;

public class ConversationUsecase {


    // Placeholder for actual implementation
    public List<SelectConvDTO> selectConversations(UUID roomid, String username) {
        return List.of(new SelectConvDTO(roomid, username)); // Placeholder for actual implementation
    }


    ///////    //one to one page
    public UUID createConversation(ConversationInDTO conversationInDTO) {
        return UUID.randomUUID();
    }

    public List<ConversationOutDTO> getConversations(UUID roomid) {

       return List.of(); // Placeholder for actual implementation
    }
}