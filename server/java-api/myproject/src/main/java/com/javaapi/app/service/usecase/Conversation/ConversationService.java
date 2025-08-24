package com.javaapi.app.service.usecase.Conversation;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IMessageRepo;
import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IRoomRepo;
import com.javaapi.app.service.core.dto.ConversationDTO.ConversationInDTO;
import com.javaapi.app.service.core.entity.MessageEntity;
import com.javaapi.app.service.core.entity.RoomEntity;
import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.entity.UserEntity;

@Service
public class ConversationService {

    private final IMessageRepo messageRepository;
    private final IRoomRepo roomRepository;
    private final IUserRepo userRepository;

    public ConversationService(IMessageRepo messageRepository,
                               IRoomRepo roomRepository,
                               IUserRepo userRepository) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID handleConversation(ConversationInDTO dto) {
        RoomEntity room = roomRepository.getReferenceById(dto.getRoomid());
        UserEntity user = userRepository.getReferenceById(dto.getUserid());

        MessageEntity message = new MessageEntity(
                room,
                user,
                dto.getMessage()
        );

        MessageEntity me = messageRepository.save(message);
        return me.getId();
    }
}