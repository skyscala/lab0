/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author zeyarhtike
 */


public class TestClassAttrUtil {
    
    
//    @Test
//    public void test1(){
//        
//       
//       B b=new B();
//       b.a=createA("A1");
//       
//       
//       b.listA=Arrays.asList(createA("1"),createA("2"));
//       b.mapA=new HashMap<>();
//       b.mapA.put("oneA", createA("dataA"));
//       B b2=new B();
//       
//       ClassAttrUtil.copy(b, b2, B.class, true);
//       
//       System.out.println(b2.a.attrI);
//       System.out.println(b2.listA);
//       System.out.println(b2.mapA);
//    }
    
    //@Test
    public void test2(){
        
       Map<String,Object> map=new HashMap();
       
      
       map.put("a",createA("A1"));
       
       
       map.put("listA",Arrays.asList(createA("1"),createA("2")));
       Map mapA=new HashMap<>();
       mapA.put("oneA", createA("dataA"));
       
       //map.put("mapA",mapA);
       
       B b2=new B();
       System.out.println(b2.listA);
       System.out.println(b2.mapA);
       ClassAttrUtil.copy(map, b2, B.class, "");
       
       System.out.println(b2.a.attrI);
       System.out.println(b2.listA);
       System.out.println(b2.mapA);
    }
    
    //@Test
    public void test3(){
        
       Map<String,Object> map=new HashMap();
       
      
       map.put("a",createA("11"));
       
       
       
       
       //map.put("mapA",mapA);
       
       B b2=new B();
       //System.out.println(b2.listA);
       //System.out.println(b2.mapA);
       ClassAttrUtil.copy(map, b2, B.class, "");
       
       System.out.println(b2.a.attrI);
       //System.out.println(b2.listA);
       //System.out.println(b2.mapA);
       
       
       
       
       
    }
    
    @Test
    public void test4(){
    
        Map mapA=new HashMap();
        mapA.put("attr1", "1");
        
        Map mapB=new HashMap();
        mapB.put("attr2", "2");
        
        Map mapC=new HashMap();
        ClassAttrUtil.copy(mapA, mapC, Map.class, true);
        ClassAttrUtil.copy(mapB, mapC, Map.class, true);
        
        mapC.putAll(mapA);
        mapC.putAll(mapB);
        System.out.println(mapC);
    
    }
    
    private A createA(String val){
        A a=new A();
        a.attr1=val;
        a.attr2=val.length()*1l;
        a.attrI=val.toLowerCase();
        return a;
    }
    
    private class I{
        protected String attrI;
    }
    
    private class A extends I{
        private String attr1;
        private Long attr2;
        

        @Override
        public String toString() {
            return attr1+" "+attr2;
        }
        
        
    }
    
    private class B {
        private A a;
        private List<A> listA;
        private Map<String,A> mapA;
        
    }
    
}
