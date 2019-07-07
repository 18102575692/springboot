package com.example.demo1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "web_url")
@Data
public class WebUrl {
    @Id
    @Column(name = "path",columnDefinition = "varchar(200) COMMENT '请求地址'")
    String path;
    @Column(name = "action",columnDefinition = "varchar(10) COMMENT '请求方式'")
    String action;
    @Column(name = "status",columnDefinition = "varchar(200) COMMENT '0：公开 1：验证' default 1")
    Integer status;
}
