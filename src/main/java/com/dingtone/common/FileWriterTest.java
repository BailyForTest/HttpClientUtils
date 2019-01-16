package com.dingtone.common;

import java.io.*;

public class FileWriterTest {

    private String lineTxt;

    public void rwFile( String filepath,String string){
        FileWriter fw = null;
        try {
            fw = new FileWriter(filepath,true);
            fw.write(string);//这里向文件中写入
            fw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void crateFile(String filepath){
        File file1 = new File(filepath);
        try{
            file1.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearFile(String filepath){
        File file =new File(filepath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String rdFile(String filepath) {
        String encoding = "GBK";
        File file = new File(filepath);
        if (file.isFile() && file.exists()) {
            try {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuffer sb = new StringBuffer();
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //System.out.println(lineTxt);
                    return lineTxt;
                }
                read.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("not support the encoding");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("not find the file");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("read file fail");
            }
        }
        return lineTxt;
    }

    public static void main(String[] args) {
        FileWriterTest fileWriterTest = new FileWriterTest();
        //fileWriterTest.rdFile("F:\\File\\OrderId.txt");
        String str1 = fileWriterTest.rdFile("F:\\File\\OrderId.txt");
        String[] str = str1.split(";");
        for(String s :str){
            //System.out.println(s);
            Long id =Long.valueOf(s);
            System.out.println(id);
        }
    }
}
