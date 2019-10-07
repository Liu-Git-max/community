package com.community.controller;

import com.community.dto.CommentCreateDTO;
import com.community.dto.ResultDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.mapper.CommentMapper;
import com.community.model.Comment;
import com.community.model.User;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultDTO post(@RequestBody CommentCreateDTO commentCreateDTO,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        Map<Object,Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("message","成功");
        return ResultDTO.okOf();

    }
}

