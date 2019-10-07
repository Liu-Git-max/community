package com.community.mapper;


import com.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    //增加阅读数
    int incView(Question record);
    //增加评论数
    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

}