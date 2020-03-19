/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab3;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import org.junit.Test;
/**
 *
 * @author zeyarhtike
 */
public class TestZio {
    
    
    public void writeTempFile(String path){
        File file=new File(path);
        File dir=file.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        try(PrintWriter pw=new PrintWriter(new FileWriter(file))){
            pw.println(path);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Test
    public void test1(){
        try{
        
            writeTempFile("rootFolder/file1.txt");
            writeTempFile("rootFolder/file2.txt");

            writeTempFile("rootFolder/folder1/file11.txt");
            writeTempFile("rootFolder/folder1/file12.txt");
            writeTempFile("rootFolder/folder1/file13.txt");

            writeTempFile("rootFolder/folder2/file21.txt");
            writeTempFile("rootFolder/folder2/file22.txt");
            writeTempFile("rootFolder/folder2/file23.txt");



            Zio zio=new Zio();
            File file=new File("rootFolder");
            zio.writeZip(Arrays.asList(file.listFiles()), new File("zipFolder.zip"));
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
