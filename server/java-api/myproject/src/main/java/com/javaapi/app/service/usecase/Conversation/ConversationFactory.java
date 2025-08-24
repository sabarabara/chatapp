package com.javaapi.app.service.usecase.Conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationMemoryDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class ConversationFactory {
    //selectConversationsの変換
    public List<SelectConvDTO> toSelectConvDTO(List<IConversationDTO> dto) {
        List<SelectConvDTO> selectConvDTOs = new ArrayList<>();
        for (IConversationDTO conversation : dto) {
            SelectConvDTO selectConvDTO = new SelectConvDTO(
                    conversation.getRoomId(),
                    conversation.getUsername()
            );
            selectConvDTOs.add(selectConvDTO);
        }
        return selectConvDTOs;
    }

    //getConversationsの変換
    public List<ConversationOutDTO> toConversationOutDTO(HttpSession session, List<IConversationMemoryDTO> dto) {
        List<ConversationOutDTO> conversationOutDTOs = new ArrayList<>();
        for (IConversationMemoryDTO conversation : dto) {
            boolean isPersonal = conversation.getUserId().equals((UUID) session.getAttribute("userid"));

            ConversationOutDTO conversationOutDTO = new ConversationOutDTO(
                    conversation.getMessageId(),
                    conversation.getMessageContent(),
                    conversation.getUserId(),
                    isPersonal,
                    conversation.getUsername()
            );
            conversationOutDTOs.add(conversationOutDTO);
        }
        return conversationOutDTOs;
    }
}