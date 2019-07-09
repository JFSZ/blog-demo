package com.example.blog.service.impl;

import com.example.blog.dao.TagsMapper;
import com.example.blog.model.Tags;
import com.example.blog.service.TagsService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 陈学 on 2019-06-24 17:55:12.
 */
@Service
@Transactional
public class TagsServiceImpl extends AbstractService<Tags> implements TagsService {
    @Resource
    private TagsMapper tagsMapper;

}
