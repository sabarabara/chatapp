package com.javaapi.app.service.usecase.Conversation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationMemoryDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;


@Service
public class ConversationFactory {

    private final SessionUsecase sessionUsecase;

    public ConversationFactory(SessionUsecase sessionUsecase) {
        this.sessionUsecase = sessionUsecase;
    }

    //selectConversationsの変換
    public List<SelectConvDTO> toSelectConvDTO(List<IConversationDTO> dto) {
        List<SelectConvDTO> selectConvDTOs = new ArrayList<>();
        for (IConversationDTO conversation : dto) {

            SelectConvDTO selectConvDTO = new SelectConvDTO(
                    conversation.getRoomId(),
                    conversation.getUsername()
                    , "https://placehold.jp/150x150.png"
            );
            selectConvDTOs.add(selectConvDTO);
        }
        return selectConvDTOs;
    }

    //getConversationsの変換
    public List<ConversationOutDTO> toConversationOutDTO(String session, List<IConversationMemoryDTO> dto) {
        List<ConversationOutDTO> conversationOutDTOs = new ArrayList<>();
        for (IConversationMemoryDTO conversation : dto) {

            String sessionuserid = sessionUsecase.getUserSession(session).getUserId();
            String userid = conversation.getUserId();

            boolean isPersonal = true;

            if (!sessionuserid.equals(userid)) {
                isPersonal = false;
            }

            ConversationOutDTO conversationOutDTO = new ConversationOutDTO(
                    conversation.getMessageId(),
                    conversation.getMessageContent(),
                    "https://placehold.jp/150x150.png",
                    isPersonal,
                    conversation.getUsername()
            );
            conversationOutDTOs.add(conversationOutDTO);
        }
        return conversationOutDTOs;
    }
}