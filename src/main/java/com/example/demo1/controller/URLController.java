package com.example.demo1.controller;

import com.example.demo1.dao.dao.WebUrlDao;
import com.example.demo1.entity.WebUrl;
import com.example.demo1.tools.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

@RestController
public class URLController {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    WebUrlDao webUrlDao;
    @RequestMapping(value = "/getAllUrl")
    public String getAllUrl() {
        Map<RequestMappingInfo, org.springframework.web.method.HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> set = map.keySet();
        for (RequestMappingInfo info : set) {
            RequestMethodsRequestCondition handlerMethod = info.getMethodsCondition();
            // springmvc的url地址，不包含项目名
            PatternsRequestCondition patternsCondition = info.getPatternsCondition();
            WebUrl url = new WebUrl();
            String path=patternsCondition.toString().replace("[","").replace("]","");
            url.setPath(path);
            WebUrl webUrlResult = this.webUrlDao.select(url);
            if (webUrlResult == null){
                WebUrl webUrl = new WebUrl();
                webUrl.setAction(handlerMethod.toString().replace("[","").replace("]",""));
                webUrl.setPath(path);
                this.webUrlDao.add(webUrl);
            }
            System.out.println(patternsCondition+"》》》》"+handlerMethod);
        }
        System.out.println(111);
        return "ok";
    }
    @RequestMapping(value = "/getAllUrlByUrl")
    public ResultDto getAllUrlByUrl(@RequestParam(name = "url") String url) {
        WebUrl webUrl = new WebUrl();
        webUrl.setPath(url);
        WebUrl webUrlResult = this.webUrlDao.select(webUrl);
        return ResultDto.ok(webUrlResult);
    }
}
