/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;


import com.skyscala.generic.lab0.dummy.SourceMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author zeyarhtike
 */
public class TestObjectMap2 {
    //@Test
    public void test1(){
        try{
            Map<String,Object> map=ObjectMap2.createTree("a.b.c.d.e","123");
            System.out.println(map);
            ObjectMap.printMap(null,map);
            
            //B b=new B();
            //System.out.println("b.c="+b.getC());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Test
    public void test2(){
        Map<String,Object> map=ObjectMap2.generate( SourceMap.DATA);
        System.out.println(map);
        ObjectMap.printMap(null,map);
    }
}
