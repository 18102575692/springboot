package com.example.demo1.dao.dao;

import com.example.demo1.dao.mapper.WebUrlMapper;
import com.example.demo1.entity.WebUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebUrlDao {

    @Autowired
    WebUrlMapper webUrlMapper;

    //添加url
    public int add(WebUrl webUrl){
        return this.webUrlMapper.insertSelective(webUrl);
    }
    //查询
    public WebUrl select(WebUrl webUrl){
        return this.webUrlMapper.selectByPrimaryKey(webUrl.getPath());
    }
}
