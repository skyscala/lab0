/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skyscala.generic.lab3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author zeyarhtike
 */
public class Zio {
    
    public void writeGZip(File src,OutputStream out){
        try{  
            GZIPOutputStream gzos=new GZIPOutputStream(out);
            FileInputStream fis=new FileInputStream(src);
            int len;
            byte buffer[]=new byte[1024];
            while ((len = fis.read(buffer)) > 0) {
                    gzos.write(buffer, 0, len);
            }

            fis.close();
            gzos.finish();
            gzos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public boolean writeZip(List<File> files,File dest)throws FileNotFoundException, IOException{
        
        
        
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(dest));
        try{
            
            
                     
            for(File file:files){             	
            	writeZipHelper(zos, file,null);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        zos.closeEntry();
        zos.close();
        return true;
        
    }
    
    public boolean writeZip(List<File> files,OutputStream out)throws FileNotFoundException, IOException{
        
        
        
        ZipOutputStream zos=new ZipOutputStream(out);
        try{
            for(File file:files){             	
            	writeZipHelper(zos, file,null);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        zos.closeEntry();
        zos.close();
        return true;
        
    }
    
    private void writeZipHelper(ZipOutputStream zos,File file,String parent)throws IOException{
    	
        if(file.isFile()){
        	int bufSize=1024;
            byte[] data=new byte[bufSize];
            String entryPath=file.getName();
            if(parent!=null){
            	entryPath=parent+"/"+file.getName();
            }        	
            System.out.println(entryPath);
            ZipEntry entry=new ZipEntry(entryPath);
            zos.putNextEntry(entry);
            FileInputStream fis=new FileInputStream(file);
            int length=0;
            while((length=fis.read(data))>0){
                zos.write(data, 0, length);
            }
            fis.close();
        }else if(file.isDirectory()){
        	for(File f:file.listFiles()){  
        		if(parent!=null){
        			writeZipHelper(zos, f,parent+"/"+file.getName());
        		}else{
        			writeZipHelper(zos, f,file.getName());
        		}
        	}
        	
        }
    	
    }
    
    
}
