package com.javaapi.app.service.usecase.Forum;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.dto.ForumDTO.ForumOutDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumMemoryDTO;
import com.javaapi.app.service.core.dto.ForumDTO.SelectForumDTO;

@Service
public class ForumFactory {

    //selectForumsの変換
    public List<SelectForumDTO> toSelectForumDTO(List<IForumDTO> dto) {
        List<SelectForumDTO> selectForumDTOs = new ArrayList<>();
        for (IForumDTO forum : dto) {
            SelectForumDTO selectForumDTO = new SelectForumDTO(
                    forum.getRoomId(),
                    forum.getUsername(),
                    forum.getTitle()
            );
            selectForumDTOs.add(selectForumDTO);
        }
        return selectForumDTOs;
    }

    //getForumsの変換
    public List<ForumOutDTO> toForumOutDTO(List<IForumMemoryDTO> dto, UUID userId) {
        List<ForumOutDTO> forumOutDTOs = new ArrayList<>();
        for (IForumMemoryDTO forum : dto) {
            boolean isPersonal = forum.getUserId().equals(userId);

            ForumOutDTO forumOutDTO = new ForumOutDTO(
                    forum.getMessageId(),
                    forum.getMessageContent(),
                    forum.getUserId(),
                    isPersonal,
                    forum.getUsername()
            );
            forumOutDTOs.add(forumOutDTO);
        }
        return forumOutDTOs;
    }
}