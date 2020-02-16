/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;


import com.skyscala.generic.lab0.dummy.B;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author zeyarhtike
 */
public class TestObjectMap {
    
    @Test
    public void test1(){
        try{
            Map<String,Object> map=ObjectMap.create(B.class, null);
            System.out.println(map);
            ObjectMap.printMap(null,map);
            
            //B b=new B();
            //System.out.println("b.c="+b.getC());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
