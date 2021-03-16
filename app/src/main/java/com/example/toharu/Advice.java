package com.example.toharu;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Advice {
    private String advice;
    private int    adviceCount = 0;
    private Map<String, Boolean> advices = new HashMap<>();

    public Advice() {}

    public Advice(String advice) {
        this.advice = advice;
    }

    public Advice(Dictionary<String, Object> dictionary){
        this.advice = dictionary.get("advice").toString();
    }

   public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("", advice);

        return result;
   }
}
