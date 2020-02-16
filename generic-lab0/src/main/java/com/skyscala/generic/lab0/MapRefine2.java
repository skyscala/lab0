/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;

import static com.skyscala.generic.lab0.MapRefine.adjust;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author zeyarhtike
 */
public class MapRefine2 {
    
    
    public static void  process(String initial,Map<String,Object> map,String dli){
        
        
        int size=1;
        
        
        for(String key:map.keySet()){
            Object temp=map.get(key);
            if(temp==null){continue;}       
            if(temp instanceof Map){
                //process(key,(Map)temp, dli);
                //processMap.put(key,process((Map)temp, dli));
                
            }else{
                String val = temp.toString();
                if(val.contains(dli)){
                    String[] arr=val.split(dli);
                    int len=arr.length;
                    if(len>size){
                        size=len;
                    }
                    //processMap.put(key, Arrays.asList(arr));
                }
            }
        }
        
        
        if(size>1){
            List list=new ArrayList();
            for(int i=0;i<size;i++){                
                Map collection=new TreeMap();
                for(String key:map.keySet()){
                    Object temp=map.get(key);
                    if(temp==null){continue;} 
                    String val = temp.toString();
                    if(val.contains(dli)){
                        String[] arr=val.split(dli);
                        if(arr.length>i){
                            collection.put(key, arr[i]);
                        }else{
                            collection.put(key, null);
                        }
                        //processMap.put(key, Arrays.asList(arr));
                    }
                }
                list.add(collection);
            }
            map.put(initial, list);
        }
        
        
        
                
    }
}
