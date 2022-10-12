package com.socle.springboot.testUnitaire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestJunitService {

    public String getMessage(String name) {

        return "Hello" + name + ",Thanks";
    }

    public Integer getSum(Integer a, Integer b) {
        return a + b;
    }

    public Object getObject(Object o) {
        return o;
    }

    public Map<String, Integer[]> testArray(Integer a, Integer b, Integer c) {
        Map<String, Integer[]> map = new HashMap<String, Integer[]>();
        Integer[] arr1 = new Integer[]{a, b, c};
        Integer[] arr2 = new Integer[]{1, 2, 3};

        map.put("arr1", arr1);
        map.put("arr2", arr2);

        return map;

    }
}
