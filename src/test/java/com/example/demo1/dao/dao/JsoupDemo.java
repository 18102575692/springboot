package com.example.demo1.dao.dao;


import com.example.demo1.entity.Dish;
import com.example.demo1.tools.GlobalConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 获取排行一周热门的菜单
 * @author xiesj
 */
public class JsoupDemo {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(GlobalConstant.WEEK_RECOMMENDED_URL).get();
        Elements elements_all = new Elements();
        //获取一周热门的数据
        Elements elements = document.getElementsByClass("ui_newlist_1 get_num");
        for (Element element:elements){
            Elements elements1 = element.getElementsByTag("li");
            for (Element element1 :elements1){
                Element A = element1.getElementsByTag("a").get(0);
                System.out.println("菜的详情带爬取的数据："+A.attr("href"));
            }
        }


    }
}
