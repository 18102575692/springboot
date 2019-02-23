package com.example.demo1.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//j接收前端表单数据
public class UserForm {
    @NotNull(message = "用户ID不能为空")
    private Integer id;
    @Size(max = 50,message = "用户名不允许超出500个字")
    @NotBlank(message = "用户名不不允许为空")
    private String name;
    @NotBlank(message = "用户密码不允许为空")
    private String password;
    @NotBlank(message = "手机号码不允许为空")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
