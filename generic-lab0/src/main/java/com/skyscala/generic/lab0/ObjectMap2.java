/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;


import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author zeyarhtike
 */
public class ObjectMap2 {

    /*
    
        a1.b1.c2
        a1.b2
        a1.b1.c3
    
    
        a1{b1{c2}}
        a1{b2}
        a1{b1{c3}}
    
        a1{b1{c2,c3},b2}
    
    
    */
    
    
    
    
    public static Map<String,Object> generate(Map<String,String> data){
    
        Map<String,Object> map= new TreeMap<>();        
        
        data.keySet().forEach((k) -> {            
            if(k.contains(".")){
                mergeMap(createTree(k,data.get(k)),map);
            }else{
                Map m=new TreeMap();
                m.put(k, data.get(k));
                mergeMap(m,map);
            }
        });
        
        return map;
    }
    
    /**
     * 
     * @param src Source Map
     * @param des Destination Map
     */
    public static void mergeMap(Map src,Map des){
        
        src.keySet().forEach((k) -> {
            Object srcV=src.get(k);
            Object desV=des.get(k);
            if(desV!=null){
                if((srcV instanceof Map) && (desV instanceof Map)){
                    mergeMap((Map)srcV,(Map)desV);
                }
            }else{
                des.put(k, srcV);
            }
        });
    
    }
    
    /**
     * 
     * @param path
     * @param data
     * @return 
     */
    public static Map<String,Object> createTree(String path,Object data){
        Map<String,Object> map=new TreeMap<>();
        if(path.contains(".")){
            String[] arr=path.split("\\.");
            map.put(arr[0], createTree(path.replaceFirst(arr[0]+".", ""), data));            
        }else{
            map.put(path, data);
        }
        return map;
    }
    
    
    
}
