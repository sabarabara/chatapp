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

import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/forum")
public class ForumController{

    private final ForumUsecase forumUsecase;

    
    public ForumController(ForumUsecase forumUsecase) {
        this.forumUsecase = forumUsecase;
    }


    //select page 
    @RequestMapping("/select")
    public List<SelectForumDTO> selectForums(HttpSession session) {
        return forumUsecase.selectForums(session);
    }


    //one to one page 
    @RequestMapping("/create")
    public UUID createForum(@RequestBody ForumInDTO forumInDTO) {

        return forumUsecase.createForum(forumInDTO);
    }

    @RequestMapping("/get")
    public List<ForumOutDTO> getForums(@RequestBody UUID roomid, HttpSession session) {
        return forumUsecase.getForums(roomid, session);
    }
}