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
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;

import jakarta.servlet.http.HttpSession;

@Service
public class ConversationUsecase {

    private final IConversationRepo conversationRepo;
    private final ConversationFactory conversationFactory;
    private final SessionUsecase sessionUsecase;
    private final ConversationService conversationService;

    public ConversationUsecase(IConversationRepo conversationRepo, ConversationFactory conversationFactory, SessionUsecase sessionUsecase, ConversationService conversationService) {
        this.conversationRepo = conversationRepo;
        this.conversationFactory = conversationFactory;
        this.sessionUsecase = sessionUsecase;
        this.conversationService = conversationService;
    }



    public List<SelectConvDTO> selectConversations(HttpSession session) {
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();
        List<IConversationDTO> conversationDTO = conversationRepo.findConnectedUsersInTwoMemberRooms(userid, null);
        System.out.println("conversationDTO:" + conversationDTO);
        //ここからfactoryで変換
        List<SelectConvDTO> selectConvDTOs = conversationFactory.toSelectConvDTO(conversationDTO);
        return selectConvDTOs;
    }


    ///////    //one to one page
    public UUID createConversation(ConversationInDTO conversationInDTO) {
        return conversationService.handleConversation(conversationInDTO);
    }

    public List<ConversationOutDTO> getConversations(HttpSession session, UUID roomid) {

        List<IConversationMemoryDTO> conversationMemoryDTOs = conversationRepo.findMessagesByRoomId(roomid);
        //ここからfactoryで変換
        List<ConversationOutDTO> conversationOutDTOs = conversationFactory.toConversationOutDTO(session, conversationMemoryDTOs);
        return conversationOutDTOs;
    }
}