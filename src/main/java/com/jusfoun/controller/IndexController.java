package com.jusfoun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public Map<String, Object> index() {
        return new HashMap<>();
    }
}
