package com.example.demo1.dao.dao;

import cn.hutool.crypto.SmUtil;
import org.junit.Test;

import java.util.Random;


public class SmUtilTest {
    @Test
    public void sm3(){
        for (int i=0;i<3;i++)
            System.out.println("第"+i+"次加密结果"+SmUtil.sm3("aaaaa"));
    }

    @Test
    public void r(){
        String str="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        System.out.println(sb.toString());
    }
}
