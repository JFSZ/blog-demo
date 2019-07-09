package com.example.blog.service.impl;

import com.example.blog.dao.ArticleTagsMapper;
import com.example.blog.model.ArticleTags;
import com.example.blog.service.ArticleTagsService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 陈学 on 2019-06-24 17:54:29.
 */
@Service
@Transactional
public class ArticleTagsServiceImpl extends AbstractService<ArticleTags> implements ArticleTagsService {
    @Resource
    private ArticleTagsMapper articleTagsMapper;

}
