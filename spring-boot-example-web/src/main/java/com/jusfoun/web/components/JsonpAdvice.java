package com.jusfoun.web.components;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * http://localhost:8080/user/userInfo/93201214-3b98-11e7-8b90-efc0f68ee6d7?callback=userJsonp
 * Create on 2017-06-22.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}
