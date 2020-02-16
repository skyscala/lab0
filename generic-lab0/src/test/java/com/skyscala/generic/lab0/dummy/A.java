/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab0.dummy;

import java.util.Date;

/**
 *
 * @author zeyarhtike
 */
public class A extends I{
    private String attr1;
        private Long attr2;
        private Date date;

    /**
     * @return the attr1
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * @param attr1 the attr1 to set
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * @return the attr2
     */
    public Long getAttr2() {
        return attr2;
    }

    /**
     * @param attr2 the attr2 to set
     */
    public void setAttr2(Long attr2) {
        this.attr2 = attr2;
    }
}
