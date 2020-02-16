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
    
        DATA.put("attr1.name", "A##B##C##D##E");
        DATA.put("attr1.value", "10");
        DATA.put("attr2.attr1.name", "A1##B1##C1##D1##E1");
        DATA.put("attr2.attr1.value", "100");        
        DATA.put("date","2019/Feb/15");
        DATA.put("name", "record1");
        
        
    
    }
    
}
