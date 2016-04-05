package com.sample.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by U0128754 on 3/25/2016.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerErr(HttpServletRequest req, Exception ex) {
        ModelAndView model = new ModelAndView("errors");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());
        model.addObject("errMsg", "this is Exception.class");

        return model;
    }

}
