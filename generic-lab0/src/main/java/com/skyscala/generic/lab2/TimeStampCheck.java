/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab2;

import java.util.Date;

/**
 *
 * @author zeyarhtike
 */
public class TimeStampCheck {
    
    public static void main(String... args){
        long l=1576341036493l;
        Date d= new Date(l);
        System.out.println(d);
    }
}
