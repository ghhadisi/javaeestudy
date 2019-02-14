package com.hss.mall.utils;

import java.util.Iterator;
import java.util.Map;

public class Print {

    public static void paramap(Map<String, String[]> params){
        if (!params.isEmpty()){
            Iterator<String> iterator =  params.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                System.out.print(key + "  =  ");
                System.out.println(params.get(key)[0]);

            }
        }
    }
}
