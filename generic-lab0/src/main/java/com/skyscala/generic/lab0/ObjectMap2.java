/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;

import java.util.ArrayList;
import java.util.List;
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
        
        
        for(String k:data.keySet()){            
            if(k.contains(".")){
                mergeMap(createTree(k,data.get(k)),map);
            }else{
                Map m=new TreeMap();
                m.put(k, data.get(k));
                mergeMap(m,map);
            }
        }
        
        return map;
    }
    
    public static void mergeMap(Map src,Map dest){
        
        for(Object k:src.keySet()){
            Object srcV=src.get(k);
            Object desV=dest.get(k);
            if(desV!=null){
                if((srcV instanceof Map) && (desV instanceof Map)){
                    mergeMap((Map)srcV,(Map)desV);
                }
            }else{
                dest.put(k, srcV);
            }
        }
    
    }
    
    
    public static Map<String,Object> createTree(String str,Object data){
        Map<String,Object> map=new TreeMap<>();
        if(str.contains(".")){
            String[] arr=str.split("\\.");
//            if(arr.length==1){                
//                Map newMap=new TreeMap();
//                newMap.put(arr[0], data);
//                return newMap;
//            }else{
//                String first=arr[0];
//                Object val=map.get(first);
//                if(val==null){
//                    val=new TreeMap();
//                    map.put(first, val);
//                }
//                if(arr.length>1){
//                    ((Map)val).put(arr[1],createTree(str.replaceFirst(arr[0]+"."+arr[1]+".", ""),data));
//                }
//            }

            
//                String first=arr[0];
//                Object val=map.get(first);
//                if(val==null){
//                    //val=new TreeMap();
//                    map.put(arr[0], createTree(str.replaceFirst(arr[0]+".", ""), data));
//                }
                map.put(arr[0], createTree(str.replaceFirst(arr[0]+".", ""), data));
                //((Map)val).put(arr[1],createTree(str.replaceFirst(arr[0]+".", ""), data));
                //((Map)val).put(arr[1],createTree(str.replaceFirst(arr[0]+"."+arr[1]+".", ""),data));
                
            
        }else{
            map.put(str, data);
        }
        return map;
    }
    
    
    
}
