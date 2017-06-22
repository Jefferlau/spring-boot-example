package com.jusfoun.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Create on 2017-06-14.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
public class JsonPathTests {

    private JSONObject jsonObject;

    @Before
    public void init() throws IOException {
        InputStream resourceAsStream = JsonPathTests.class.getResourceAsStream("/content.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));

        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            sb.append(line);
            line = bufferedReader.readLine();
        }

        jsonObject = (JSONObject) JSON.parse(sb.toString());
    }

    @Test
    public void fastjson() {
        ArrayList<JSONArray> eval = (ArrayList<JSONArray>) JSONPath.eval(jsonObject, "$..subContents");
        for (JSONArray jsonArray : eval) {
            System.out.println(jsonArray.size());
        }
        System.out.println(JSONPath.eval(jsonObject, "$..subContents"));
    }

    @Test
    public void jsonPath() {
        net.minidev.json.JSONArray read = JsonPath.read(jsonObject, "$..subContents");
        System.out.println(read);
    }
}
