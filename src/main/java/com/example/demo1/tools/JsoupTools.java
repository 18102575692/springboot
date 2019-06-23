package com.example.demo1.tools;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.Dish;
import com.example.demo1.service.DishService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * 获取排行一周热门的菜单
 * @author xiesj
 */
@Service
public class JsoupTools{

    @Autowired
    DishService dishService;

    public static void main(String[] args) throws IOException, InterruptedException {
        new JsoupTools().getDish();
    }

    /**
     * 一周前十推荐
     */
    public void getDish() throws IOException, InterruptedException {
        Document document = Jsoup.connect(GlobalConstant.WEEK_RECOMMENDED_URL).get();
        Set<String> url = new HashSet<>();
        //获取一周热门的数据
        Elements elements = document.getElementsByClass("ui_newlist_1 get_num");
        for (Element element:elements){
            Elements elements1 = element.getElementsByTag("li");
            for (Element element1 :elements1){
                Element A = element1.getElementsByTag("a").get(0);
                url.add(A.attr("href"));
            }
        }
        //获取详情信息
        for (String string : url){
            Element dish_details = Jsoup.connect(string).get().getElementsByClass("recipDetail").get(0);
            Dish dish = new Dish();
            //设置id
            dish.setDish_id(dish_details.getElementById("recipe_id").attr("value"));
            //设置名称
            dish.setDish_name(dish_details.getElementById("recipe_title").attr("value"));
            //设置图片
            dish.setImage_url(dish_details.getElementById("recipe_De_imgBox")
                    .getElementsByTag("a").get(0)
                    .getElementsByTag("img").attr("src"));
            //设置主辅料
            Elements fieldset = dish_details.getElementsByTag("fieldset");
            for (Element element : fieldset){
                String material = element.getElementsByTag("legend").get(0).text();
                //判断原料
                if (material.equals("主料")){
                    Elements main = element.getElementsByClass("recipeCategory_sub_R clear").get(0).getElementsByTag("li");
                    JSONArray jsonArray = new JSONArray();
                    for (Element element1 : main){
                        String name = element1.getElementsByTag("b").text();
                        String weight = element1.getElementsByClass("category_s2").text();
                        JSONObject main_material = new JSONObject();
                        main_material.put("weight",weight);
                        main_material.put("name",name);
                        jsonArray.add(main_material);
                    }
                    dish.setMain_material(jsonArray.toString());
                }
                if (material.equals("辅料")){
                    Elements main = element.getElementsByClass("recipeCategory_sub_R clear").get(0).getElementsByTag("li");
                    JSONArray jsonArray = new JSONArray();
                    for (Element element1 : main){
                        String name = element1.getElementsByTag("b").text();
                        String weight = element1.getElementsByClass("category_s2").text();
                        JSONObject other_material = new JSONObject();
                        other_material.put("weight",weight);
                        other_material.put("name",name);
                        jsonArray.add(other_material);
                    }
                    dish.setOther_materials(jsonArray.toString());
                }
            }
            Elements other_information = dish_details.getElementsByClass("recipeCategory_sub_R mt30 clear").get(0).getElementsByTag("li");
            for (Element element : other_information){
                String information_type = element.getElementsByClass("category_s2").get(0).text();
                //设置口味
                if (information_type.equals("口味")){
                    dish.setDish_taste(element.getElementsByTag("a").get(0).text());
                }
                //设置工艺
                if (information_type.equals("工艺")){
                    dish.setDish_technology(element.getElementsByTag("a").get(0).text());
                }
                //设置耗时
                if (information_type.equals("耗时")){
                    dish.setTime_consuming(element.getElementsByTag("a").get(0).text());
                }
                //设置难度
                if (information_type.equals("难度")){
                    dish.setDish_difficulty(element.getElementsByTag("a").get(0).text());
                }
            }
            //设置步骤
            Elements step = dish_details.getElementsByClass("recipeStep").get(0).getElementsByTag("li");
            JSONArray stepElement = new JSONArray();
            for (Element element : step){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("step",element.getElementsByClass("recipeStep_num").text());
                jsonObject.put("details",element.getElementsByClass("recipeStep_word").text());
                jsonObject.put("url",element.getElementsByTag("img").get(0).attr("src"));
                stepElement.add(jsonObject);
            }
            stepElement.sort(Comparator.comparing(obj ->((JSONObject)obj).getString("step")));
            dish.setDish_describe(stepElement.toString());
            dish.setWeek_time(Integer.parseInt(DateUtil.today().replace("-","")));
            this.dishService.create(dish);
        }
    }
}
