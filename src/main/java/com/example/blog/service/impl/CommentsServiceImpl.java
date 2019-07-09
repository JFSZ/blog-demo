package com.example.blog.service.impl;

import com.example.blog.dao.CommentsMapper;
import com.example.blog.model.Comments;
import com.example.blog.service.CommentsService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 陈学 on 2019-06-24 17:54:49.
 */
@Service
@Transactional
public class CommentsServiceImpl extends AbstractService<Comments> implements CommentsService {
    @Resource
    private CommentsMapper commentsMapper;

}
