package com.javaapi.app.service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.ForumDTO.ForumInDTO;
import com.javaapi.app.service.core.dto.ForumDTO.ForumOutDTO;
import com.javaapi.app.service.core.dto.ForumDTO.SelectForumDTO;
import com.javaapi.app.service.usecase.Forum.ForumUsecase;
@RestController
@RequestMapping("/forum")
public class ForumController{

    private final ForumUsecase forumUsecase;

    public ForumController(ForumUsecase forumUsecase) {
        this.forumUsecase = forumUsecase;
    }


    //select page 
    public List<SelectForumDTO> selectForums(UUID roomid, String username) {
        return forumUsecase.selectForums(roomid, username);
    }


    //one to one page 
    public UUID createForum(@RequestBody ForumInDTO forumInDTO) {

        return forumUsecase.createForum(forumInDTO);
    }

    public List<ForumOutDTO> getForums(UUID roomid, UUID userid) {
        return forumUsecase.getForums(roomid, userid);
    }
}