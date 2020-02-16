/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0.dummy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zeyarhtike
 */
public class SourceMap {
    
    public static final Map DATA=new HashMap();
    
    static{
        //DATA.put("attr0", "1##2");
        DATA.put("attr1.name", "A##B##C##D##E");
        DATA.put("attr1.name1", "1##2");
        DATA.put("attr1.value", "10");
        DATA.put("attr2.attr1.name", "A1##B1##C1##D1##E1");
        DATA.put("attr2.attr1.value", "100");   
        DATA.put("attr3.attr2.attr1.name", "A1##B1##C1##D1##E1");
        DATA.put("attr3.attr2.attr1.value", "100");   
        DATA.put("attr4.attr3.attr2.attr1.name", "A1##B1##C1##D1##E1");
        DATA.put("attr4.attr3.attr2.attr1.value", "100");
        DATA.put("date","2019/Feb/15");
        DATA.put("name", "record1");
        
        
    
    }
    
    /*
    [
        {name=A, name1=1, value=10}, 
        {name=B, name1=2, value=10}, 
        {name=C, name1=null, value=10}, 
        {name=D, name1=null, value=10}, 
        {name=E, name1=null, value=10}
    ]
    */
    /*
    [
        {
            attr1=[
                {name=A1, value=100}, 
                {name=B1, value=100}, 
                {name=C1, value=100}, 
                {name=D1, value=100}, 
                {name=E1, value=100}
            ]
        }, 
        {
            attr1=[
                {name=A1, value=100}, 
                {name=B1, value=100}, 
                {name=C1, value=100}, 
                {name=D1, value=100}, 
                {name=E1, value=100}
            ]
        }
    ]
    */
    /*
    [
        {
            attr2=[
                {
                    attr1=[
                        {name=A1, value=100}, 
                        {name=B1, value=100}, 
                        {name=C1, value=100}, 
                        {name=D1, value=100}, 
                        {name=E1, value=100}
                    ]
                }
            ]
        }, 
        {   attr2=[
                {
                    attr1=[
                        {name=A1, value=100}, 
                        {name=B1, value=100}, 
                        {name=C1, value=100}, 
                        {name=D1, value=100}, 
                        {name=E1, value=100}
                    ]
                }
            ]
        }
    ]
    */
    
    /*
        [
            {attr3=[
                {attr2=[
                    {attr1=[
                        {name=A1, value=100}, 
                        {name=B1, value=100}, 
                        {name=C1, value=100}, 
                        {name=D1, value=100}, 
                        {name=E1, value=100}]
                    }
                ]
                }
            ]}, 
            {attr3=[
                {attr2=[
                    {attr1=[
                        {name=A1, value=100}, 
                        {name=B1, value=100}, 
                        {name=C1, value=100}, 
                        {name=D1, value=100}, 
                        {name=E1, value=100}]
                    }
                ]
                }
            ]}
    ]
    */
}
