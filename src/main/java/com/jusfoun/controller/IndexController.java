package com.jusfoun.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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

    @GetMapping("/info")
    public Map<String, Object> info(@RequestAttribute("name") @ApiParam(value = "名称", required = true) String name) {
        Map<String, Object> map =  new HashMap<>();
        map.put("name", name);
        return map;
    }
}
