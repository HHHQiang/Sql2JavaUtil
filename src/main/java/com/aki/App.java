package com.aki;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    public static void main(String[] args)throws Exception {
        String javaFile = "C:\\Users\\170725e\\Desktop\\java.txt";
        String sqlFile = "C:\\Users\\170725e\\Desktop\\sql.txt";
        sql2Java(sqlFile, javaFile);
        // 第一次注释
        // 第二次注释
        // 第三次 测试rebase1
        // disici
    }


    /**
     * 将格式化后的sql文件,或普通的txt文件,读取每一行sql语句,用"sb.append("")来包裹"
     * @param sqlPath   sql文件的路径
     * @param javaFile  java文件的路径
     */
    private static void sql2Java(String sqlPath, String javaFile)throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(sqlPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(javaFile, false));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date;
        date = format.format(new Date());
        String str = "";
        while((str = br.readLine()) != null) {
            if (!str.trim().equals("")){
                str = str.trim();
                int i = -1;
                //去掉注释
                if ((i = str.indexOf("-- ")) != -1){
                    str = str.substring(0,i);
                }
                if (!str.trim().equals("")){
                    bw.write(" "+ "sql" +".append(\" " + str.trim() + " \");");
                    bw.newLine();
                }
            }
        }
        bw.close();
        br.close();
        System.out.println("java文件输出ok");
    }

    /**
     * 将java代码中被"sb.append("")来包裹的sql文件,还原成原来的sql
     * @param var       stringbuffer的变量名
     * @param javaPath  java文件的路径
     * @param sqlPath   sql文件的路径
     * @param isAppend  是否在原文件后拼接
     */
    public static void java2Sql(String var,String javaPath,String sqlPath,boolean isAppend)throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(javaPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(sqlPath));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String str = "";
        if (isAppend){
            bw.newLine();
            bw.write("--------------------"+date+"-------------------------");
            bw.newLine();
        }
        while((str = br.readLine()) != null) {
            bw.write(str.trim().replace(var+".append(\"","").replace("\");",""));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
        System.out.println("sql文件输出ok");
    }

}
