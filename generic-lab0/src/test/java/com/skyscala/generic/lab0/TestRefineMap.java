/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;

import com.skyscala.generic.lab0.dummy.SourceMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author zeyarhtike
 */
public class TestRefineMap {

    //@Test
    public void test1() {

        try {
            Map<String, Object> map = ObjectMap2.generate(SourceMap.DATA);
            System.out.println(map);
            ObjectMap.printMap(null, map);

            List result = MapRefine.makeList(map, "##");
            System.out.println("------");
            for (Object r : result) {
                System.out.println(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Map<String, Object> map = ObjectMap2.generate(SourceMap.DATA);
            System.out.println(map);
            ObjectMap.printMap(null, map);

            Map<String, Object> result = MapRefine.process(map, "##");
            System.out.println("------");
            for (String k : result.keySet()) {
                Object r = result.get(k);
                System.out.println(k + " - " + r);
                if (r instanceof List) {
                    List procList = MapRefine.processList((List) r);
                    System.out.println(k + " - " + procList);
                    //print(procList);
                    /*
                    int count=1;
                    for(Object item:procList){
                        if(item instanceof List){
                            print((List)item);
                        }
                        //System.out.println(k+(count++)+" - "+item);
                    }
                     */
                }
            }
            System.out.println("------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void print(List list) {
        for (Object o : list) {
            if (o instanceof Map) {
                Map m = (Map) o;
                print(m);
            } else {
                System.out.println(o);
            }
        }
    }

    private void print(Map m) {
        for (Object k : m.keySet()) {
            Object itm = m.get(k);
            if (k instanceof List) {
                print((List) k);
            } else {
                System.out.println(itm);
            }
        }
    }

    //@Test
    public void test3() {
        try {
            Map<String, Object> map = ObjectMap2.generate(SourceMap.DATA);
            System.out.println(map);
            ObjectMap.printMap(null, map);

            MapRefine2.process("", map, "##");
            System.out.println("------");
            System.out.println(map);
            System.out.println("------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
