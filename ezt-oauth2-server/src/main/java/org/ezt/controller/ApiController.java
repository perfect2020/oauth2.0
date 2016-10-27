package org.ezt.controller;

import org.base.pluging.APIDocumentGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/4/20.
 */
@RestController
public class ApiController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/documents",method = RequestMethod.GET)
    public void apiDocument(HttpServletRequest request, HttpServletResponse response){
        APIDocumentGenerate.createDocument(applicationContext,request,response);
    }

}
