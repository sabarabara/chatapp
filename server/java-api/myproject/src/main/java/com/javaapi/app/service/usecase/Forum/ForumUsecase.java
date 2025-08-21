package com.javaapi.app.service.usecase.Forum;

import java.util.List;
import java.util.UUID;

import com.javaapi.app.service.core.dto.ForumDTO.ForumInDTO;
import com.javaapi.app.service.core.dto.ForumDTO.ForumOutDTO;
import com.javaapi.app.service.core.dto.ForumDTO.SelectForumDTO;

public class ForumUsecase {


    public List<SelectForumDTO> selectForums(UUID roomid, String username) {
        return List.of(new SelectForumDTO(roomid, "Sample Title", username));
    }


    public UUID createForum(ForumInDTO forumInDTO) {
        return forumInDTO.getMessageid(); // Placeholder for actual implementation
    }

    public List<ForumOutDTO> getForums(UUID roomid, UUID userid) {
        return List.of(); // Placeholder for actual implementation
    }
}