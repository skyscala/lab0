/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author zeyarhtike
 */
public class MapRefine {
    
    
    public static List<Map<String,Object>> makeList(Map<String,Object> map,String dli){
        
        
        int size=1;
        
        Map<String,List<Object>> processMap=new TreeMap<>();
        for(String key:map.keySet()){
            Object temp=map.get(key);
            if(temp==null){continue;}       
            if(temp instanceof Map){
                processMap.put(key,makeList((Map)temp, dli));
                
            }else{
                String val = temp.toString();
                if(val.contains(dli)){
                    String[] arr=val.split(dli);
                    int len=arr.length;
                    if(len>size){
                        size=len;
                    }
                    processMap.put(key, Arrays.asList(arr));
                }else{
                    List<Object> multiply=new ArrayList<>();
                    multiply.add(val);
                    processMap.put(key, multiply);
                }
            }
        }
        
        
        
        /*
        for(String key:map.keySet()){
            Object temp=map.get(key);
            if(temp==null){continue;}
            if(temp instanceof Map){
                processMap.put(key,makeList((Map)temp, dli));
                continue;
            }
            String val = temp.toString();
            if(!val.contains(dli)){
                List<Object> multiply=new ArrayList<>();
                for(int i=0;i<size;i++){
                    multiply.add(val);
                }
                processMap.put(key, multiply);
            }
        }
        */
        
        for(Object obj:processMap.values()){
            System.out.println(obj);
        }
        
        return makeList(processMap, size);
                
    }
    
    
    public static Map<String,Object> adjust(Map<String,Object> processMap,int size){
        
        Map<String,Object> result=new TreeMap<>();
        
        
            
            for(String k:processMap.keySet()){
                Object obj=processMap.get(k);
                if(!(obj instanceof List)){
                    List list=new ArrayList();
                    for(int i=0;i<size;i++){
                        list.add(obj);
                    }
                    result.put(k, list);
                }else{
                    result.put(k, obj);
                }
            }
            
            
        
        return result;
    }
    
    public static Map<String,Object>  process(Map<String,Object> map,String dli){
        
        
        int size=1;
        
        Map<String,Object> processMap=new TreeMap<>();
        for(String key:map.keySet()){
            Object temp=map.get(key);
            if(temp==null){continue;}       
            if(temp instanceof Map){
                //process((Map)temp, dli);
                processMap.put(key,process((Map)temp, dli));
                
            }else{
                String val = temp.toString();
                if(val.contains(dli)){
                    String[] arr=val.split(dli);
                    int len=arr.length;
                    if(len>size){
                        size=len;
                    }
                    processMap.put(key, Arrays.asList(arr));
                }
            }
        }
        
        
        for(String key:map.keySet()){
            Object temp=map.get(key);
            if(temp==null){continue;} 
            if(temp instanceof Map){
                //processMap.put(key,process((Map)temp, dli));
                //continue;
            }else{
                String val = temp.toString();
                if(!val.contains(dli)){
                    if(size>1){
                        List<Object> multiply=new ArrayList<>();
                        for(int i=0;i<size;i++){
                            multiply.add(val);
                        }
                        processMap.put(key, multiply);
                    }else{
                        processMap.put(key, val);
                    }
                }
            }
        }
        
        
        
        
        return adjust(processMap,size);
                
    }
    
    
    public static List<Map<String,Object>> makeList(Map<String,List<Object>> processMap,int size){
        List<Map<String,Object>> result=new ArrayList<>();
        for(int i=0;i<size;i++){
            Map<String,Object> map=new TreeMap<>();
            for(String k:processMap.keySet()){
                List<Object> list=processMap.get(k);
                if(size>list.size()){
                    while(size==list.size()){
                        list.add(null);
                    }
                }
                System.out.println(k+"-"+list);
                if(list.size()==size){
                    map.put(k, list.get(i));
                }else{
                    map.put(k, list.get(0));
                }
            }
            
            result.add(map);            
        }
        return result;
    }
    
    
    /*
        [{name=[A, B, C, D, E], value=[10, 10, 10, 10, 10]}]
        [{name=A,value=10},{name=B,value=10}]
    */
    
    
    public static List processList(List list){
        
        boolean flag=false;
        int size=0;
        for(Object obj:list){
            if(obj instanceof Map){
                Map m=(Map)obj;
                for(Object k:m.keySet()){
                    Object o=m.get(k);
                    if(o instanceof List){
                        List l2=(List)o;
                        for(Object o2:l2){
                            if(!(o2 instanceof Map)){                                
                                flag=true;
                                break;
                            }
                        }    
                        if(flag==false){
                            m.put(k, processList(Arrays.asList(l2.get(0))));
                        }
                        int len=((List)o).size();
                        if(size<len){
                            size=len;
                        }
                    }
                }
            }
        }
        List result=new ArrayList();
        if(flag){
            
            for(int index=0;index<size;index++){
                Map collection=new TreeMap();
                for(Object obj:list){
                    if(obj instanceof Map){
                        Map m=(Map)obj;
                        
                        for(Object k:m.keySet()){

                            Object o=m.get(k);

                            if(o instanceof List){
                                //System.out.println("size:"+size);
                                List list2=(List)o;

                                //for(int index=0;index<size;index++){
                                    if(list2.size()>index){
                                        //System.out.println(list2.get(index));
                                        Object o2=list2.get(index);
                                        //System.out.println(k+" - " +o2.getClass());
                                        /*
                                        if(o2 instanceof Map){
                                            //System.out.println(o2);
                                            Map m2=(Map)o2;
                                            for(Object k2:m2.keySet()){
                                                Object o3=m2.get(k2);
                                                if(o3 instanceof List){
                                                    collection.put(k2, processList((List)o3));
                                                }
                                            }
                                            //collection.put(k, processList((List)o2));
                                        }else{
                                            collection.put(k, o2);
                                        }
                                        */
                                        collection.put(k, o2);
                                        
                                    }else{
                                        collection.put(k, null);
                                    }
                                //}
                                
                            }

                        }

                    }
                }
                //System.out.println(collection);
                result.add(collection);
            }
            
            
            return result;
        }
        
        
        //Collections.sort(refine);
        return list;
    }
    
    //[{attr1=[{name=[A1, B1, C1, D1, E1], value=[100, 100, 100, 100, 100]}]}]
    //[{attr1=[{name=[A1, B1, C1, D1, E1], value=[100, 100, 100, 100, 100]}]}]
}
