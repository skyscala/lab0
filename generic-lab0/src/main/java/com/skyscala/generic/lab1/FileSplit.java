/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author zeyarhtike
 */
public class FileSplit {
    
    
    public static void main(String... args){
        Map<String,String> categories=new HashMap<>();
        
        categories.put("c1", "C");
        categories.put("c2", "C");        
        categories.put("b1", "B1");
        categories.put("b2", "B");
        
        String lookup="entity";
        List<Map> list=new ArrayList<>();
        for(String c:categories.keySet()){
            int rand=(int)((Math.random()*20));            
            for(int i=0;i<rand;i++){
                Map map=new HashMap<>();
                Map head=new HashMap<>();
                head.put(lookup, c);
                String body=c+"-"+UUID.randomUUID().toString();
                map.put("head", head);
                map.put("body", body);
                list.add(map);
            }
        }
        
        Map<String,List> results=init(categories, list,lookup);
        
        for(String k:results.keySet()){
            List l=results.get(k);
            System.out.println(lookup+" - "+k);
            for(Object obj:l){
                System.out.println(obj);
            }
            System.out.println("--------------");
        }
        
    }
    
    
    public static Map<String,List> init(Map<String,String> categories,
            List<Map> list,String lookup){
            
        Map<String,List> results=new HashMap();
        
        for(Map map:list){
            
            Map head=(Map)map.get("head");            
            String body=(String)map.get("body");
            
            String catKey=(String)head.get(lookup);
            
            String category=categories.get(catKey);
            
            List filtered=results.get(category);
            if(filtered==null){
                filtered=new ArrayList();
                results.put(category, filtered);                        
            }
            
            filtered.add(body);
            
        }
        
        return results;
    }
}
