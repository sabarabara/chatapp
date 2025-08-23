package com.javaapi.app.service.usecase.Conversation;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.query.IConversationRepo;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationOutDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationMemoryDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.SelectConvDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class ConversationUsecase {

    private final IConversationRepo conversationRepo;
    private final ConversationFactory conversationFactory;

    public ConversationUsecase(IConversationRepo conversationRepo, ConversationFactory conversationFactory) {
        this.conversationRepo = conversationRepo;
        this.conversationFactory = conversationFactory;
    }



    public List<SelectConvDTO> selectConversations(UUID userid, String username) {

        List<IConversationDTO> conversationDTO = conversationRepo.findConnectedUsersInTwoMemberRooms(userid, null);
        //ここからfactoryで変換
        List<SelectConvDTO> selectConvDTOs = conversationFactory.toSelectConvDTO(conversationDTO);
        return selectConvDTOs;
    }


    ///////    //one to one page
    public UUID createConversation(ConversationInDTO conversationInDTO) {
        return UUID.randomUUID();
    }

    public List<ConversationOutDTO> getConversations(HttpSession session, UUID roomid) {

        List<IConversationMemoryDTO> conversationMemoryDTOs = conversationRepo.findMessagesByRoomId(roomid);
        //ここからfactoryで変換
        List<ConversationOutDTO> conversationOutDTOs = conversationFactory.toConversationOutDTO(session, conversationMemoryDTOs);
        return conversationOutDTOs;
    }
}