/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zeyarhtike
 */
public class CreatePolygon {
    
    public static void main(String[] args){
//        Double[] org=new Double[]{1.0,1.0};
//        
//        for(Double d:(calPoint(org, 0))){
//            System.out.println(d);
//        }
//        System.out.println("--------");
//        for(Double d:(calPoint(org, 30))){
//            System.out.println(d);
//        }
//        System.out.println("--------");
//        for(Double d:(calPoint(org, 60))){
//            System.out.println(d);
//        }
//        System.out.println("--------");
//        for(Double d:(calPoint(org, 90))){
//            System.out.println(d);
//        }

        List<Double[]> list = drawFromCenterPoint(0, 0, 12,0.25);
        
        
    }
    
    public static List<Double[]> drawFromCenterPoint(double p1,double p2,int encircles,double distance){
    
        List<Double[]> points=new ArrayList<>();
        
        Double[] org=new Double[]{p1,p2};
        
        double fac=360/encircles;
        
        for(int i=1;i<=encircles;i++){
            double d=fac*i;
            Double[] cal=calPoint(org, d,distance);
            System.out.println(d+" - ["+cal[0]+","+cal[1]+"]");
            points.add(cal);
        }
        
        return points;
        
    }
    
    private static Double[] calPoint(Double[] org, double deg,double fac){
        double rad=Math.toRadians(deg);
        return new Double[]{calX(org[0], rad,fac),calY(org[1],rad,fac)};
    }
    
    private static double calX(double x,double rad,double fac){
        BigDecimal bg=new BigDecimal(x+(Math.cos(rad)*fac));
        bg=bg.setScale(8,BigDecimal.ROUND_HALF_EVEN);
        return bg.doubleValue();
    }
    
    private static double calY(double y,double rad,double fac){
        BigDecimal bg=new BigDecimal(y+(Math.sin(rad)*fac));
        bg=bg.setScale(8,BigDecimal.ROUND_HALF_EVEN);
        return bg.doubleValue();
    }
}
