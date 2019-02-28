package com.example.demo1.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 基本用户信息
 */
@Entity
public class User implements Serializable {
    @Id
    @Column(name = "user_id",columnDefinition = "varchar(18) COMMENT '用户ID'")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "password",columnDefinition = "varchar(255) COMMENT '密码'",nullable = false)
    private String password;
    @Column(name = "status",columnDefinition = "int(4)  COMMENT '状态  1为正常 0为停用 -1为删除'",nullable = false)
    private Integer status;
    @Column(name = "phone",columnDefinition = "varchar(11) COMMENT '联系电话'")
    private String phone;
    @Column(name = "created_time",columnDefinition = "int(10) COMMENT '创建时间'",nullable = false)
    private Integer createTime;
    @Column(name = "updated_time",columnDefinition = "int(10) COMMENT '更新时间'")
    private Integer updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
