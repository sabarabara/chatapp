package com.javaapi.app.service.usecase.Forum;

import java.util.List;
import java.util.UUID;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.query.IForumRepo;
import com.javaapi.app.service.core.dto.ForumDTO.ForumInDTO;
import com.javaapi.app.service.core.dto.ForumDTO.ForumOutDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumMemoryDTO;
import com.javaapi.app.service.core.dto.ForumDTO.SelectForumDTO;

public class ForumUsecase {

    private final IForumRepo forumRepo;
    private final ForumFactory forumFactory;

    public ForumUsecase(IForumRepo forumRepo, ForumFactory forumFactory) {
        this.forumRepo = forumRepo;
        this.forumFactory = forumFactory;
    }


    public List<SelectForumDTO> selectForums(UUID roomid, String username) {
        List<IForumDTO> forumDTOs = forumRepo.findConnectedUsersInBulletinRooms(roomid, null);
        //ここからfactoryで変換
        List<SelectForumDTO> selectForumDTOs = forumFactory.toSelectForumDTO(forumDTOs);
        return selectForumDTOs;
    }


    public UUID createForum(ForumInDTO forumInDTO) {
        return forumInDTO.getMessageid(); // Placeholder for actual implementation
    }

    public List<ForumOutDTO> getForums(UUID roomid, UUID userid) {
        List<IForumMemoryDTO> forumDTOs = forumRepo.findBulletinMessagesByRoomId(roomid);
        //ここからfactoryで変換
        List<ForumOutDTO> forumOutDTOs = forumFactory.toForumOutDTO(forumDTOs, userid);
        return forumOutDTOs;
    }
}