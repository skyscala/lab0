/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author zeyarhtike
 */
public class ObjectMap {

    public static void printMap(String initial,Map map) {
        if (map == null) {
            return;
        }
        
        String prefix = (initial!=null&&!initial.trim().isEmpty())?(initial.trim()+"."):"";
        map.keySet().forEach((k) -> {
            Object val = map.get(k);
            if (val instanceof Map) {
                printMap(prefix+k+"",(Map) val);
            } else {
                
                //System.out.println(prefix+k + "= { class=" + (val!=null?val.getClass().getName():null)+",value="+val+"}");
                
                System.out.println(prefix+k + "="+val);
            }
        });
    }

    public static Map<String, Object> create( Class clz, List<Class> insClasses)
            throws IllegalAccessException, InstantiationException,ClassNotFoundException {

        //String prefix = initial != null ? (initial + ".") : "";
        String prefix="";
        Map<String, Object> map = new TreeMap<>();
        Class superClz = clz.getSuperclass();
        if (superClz != null && !superClz.isInterface()) {
            Map<String, Object> superMap = create(superClz, insClasses);
            map.putAll(superMap);
        }
        
        Class intrClasses[]=clz.getInterfaces();
        for(Class intrClz:intrClasses){
            map.putAll(create(intrClz,insClasses));
        }
        

        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            String name = field.getName();
            Class typ = field.getType();

            
            if(typ.isEnum()){
                map.put(prefix+name, newInstance(typ));
                continue;
            }
            
            if (typ.isPrimitive()) {
                map.put(prefix + name, createJavaPrimitive(typ));
                continue;
            }

            if (typ.getName().startsWith("java.")) {                
                Object interest = createJavaADT(typ);
                if (interest == null) {
                    interest = createJavaLangAndString(typ);
                }
                if(interest==null){
                    interest = newInstance(typ);
                }
                map.put(prefix + name, interest);
            } else {
                if (typ.isInterface()) {
                    Class concreteClz = chooseCandidate(typ, insClasses);
                    if (concreteClz != null) {
                        Map<String, Object> innerMap = create(concreteClz, insClasses);
                        map.put(prefix + name, innerMap);
                    } else {
                        map.put(prefix + name, null);
                    }
                } else {
                    Map<String, Object> innerMap = create(typ, insClasses);
                    map.put(prefix + name, innerMap);
                }
            }
        }

        return map;
    }

    public static Class chooseCandidate(Class intrTyp, List<Class> insClasses) {
        if (insClasses == null) {
            return null;
        }
        for (Class insClz : insClasses) {
            if (intrTyp == findDecendantClass(intrTyp, insClz)) {
                return insClz;
            }
        }
        return null;
    }

    public static Class findDecendantClass(Class targetParent, Class clz) {
        if (clz == null) {
            return null;
        }
        if (targetParent == clz) {
            return targetParent;
        } else {
            return findDecendantClass(targetParent, clz.getSuperclass());
        }
    }

    public static Object createJavaADT(Class clz) {
        System.out.println("createJavaADT - "+clz.getName());
        if (Map.class == findDecendantClass(Map.class, clz)) {
            return new TreeMap();
        } else if (List.class == findDecendantClass(List.class, clz)) {
            return new ArrayList();
        } else if (Set.class == findDecendantClass(Set.class, clz)) {
            return new TreeSet();
        }
        return null;
    }

    public static Object createJavaLangAndString(Class clz) {
        System.out.println("createJavaLangAndString - "+clz.getName());
        if (Integer.class == clz) {
            return Integer.valueOf("0");
        } else if (Long.class == clz) {
            return Long.valueOf("0");
        } else if (Float.class == clz) {
            return Float.valueOf("0.0");
        } else if (Double.class == clz) {
            return Double.valueOf("0.0");
        } else if (Byte.class == clz) {
            return Byte.valueOf("0");
        } else if (Short.class == clz) {
            return Short.valueOf("0");
        } else if (Character.class == clz) {
            return Character.MAX_VALUE;
        } else if (Boolean.class == clz) {
            return false;
        } else if (String.class == clz) {
            return "";
        }

        return null;
    }

    public static Object createJavaPrimitive(Class clz) {
        System.out.println("createJavaPrimitive - "+clz.getName());
        if (int.class == clz) {
            return Integer.valueOf("0");
        } else if (long.class == clz) {
            return Long.valueOf("0");
        } else if (float.class == clz) {
            return Float.valueOf("0.0");
        } else if (double.class == clz) {
            return Double.valueOf("0.0");
        } else if (byte.class == clz) {
            return Byte.valueOf("0");
        } else if (short.class == clz) {
            return Short.valueOf("0");
        } else if (char.class == clz) {
            return new Character('\\');
        } else if (boolean.class == clz) {
            return false;
        }

        return null;
    }
    
    public static Object newInstance(Class clz)throws ClassNotFoundException{
        try{
            return clz.newInstance();
        }catch(Exception ex){
            return clz;
        }
    }
    
    
    private static Object createAppropriateObject(Class clz){
    
        Object obj=createJavaPrimitive(clz);
        if(obj==null){
            obj=createJavaADT(clz);
        }
        if(obj==null){
            obj=createJavaLangAndString(clz);
        }
                
        return null;
    }
}
