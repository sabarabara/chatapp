package com.javaapi.app.service.usecase.Forum;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.query.IForumRepo;
import com.javaapi.app.service.core.dto.ForumDTO.ForumInDTO;
import com.javaapi.app.service.core.dto.ForumDTO.ForumOutDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumMemoryDTO;
import com.javaapi.app.service.core.dto.ForumDTO.SelectForumDTO;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;

import jakarta.servlet.http.HttpSession;

@Service
public class ForumUsecase {

    private final IForumRepo forumRepo;
    private final ForumFactory forumFactory;
    private final SessionUsecase sessionUsecase;
    private final ForumService forumService;

    public ForumUsecase(IForumRepo forumRepo, ForumFactory forumFactory, SessionUsecase sessionUsecase, ForumService forumService) {
        this.forumRepo = forumRepo;
        this.forumFactory = forumFactory;
        this.sessionUsecase = sessionUsecase;
        this.forumService = forumService;
    }


    public List<SelectForumDTO> selectForums(HttpSession session) {

        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        UUID userid = sessionDTO.getUserId();

        List<IForumDTO> forumDTOs = forumRepo.findConnectedUsersInBulletinRooms(userid, null);
        //ここからfactoryで変換
        List<SelectForumDTO> selectForumDTOs = forumFactory.toSelectForumDTO(forumDTOs);
        return selectForumDTOs;
    }


    public UUID createForum(ForumInDTO forumInDTO) {
        return forumService.handleForumPost(forumInDTO);
    }

    public List<ForumOutDTO> getForums(UUID roomid, HttpSession session) {
        List<IForumMemoryDTO> forumDTOs = forumRepo.findBulletinMessagesByRoomId(roomid);
        //ここからfactoryで変換
        List<ForumOutDTO> forumOutDTOs = forumFactory.toForumOutDTO(forumDTOs, session);
        return forumOutDTOs;
    }
}