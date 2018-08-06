package cn.xhy.test;

import cn.xhy.shop.dbc.DatabaseConnection;
import cn.xhy.shop.factory.ServiceBackFactory;
import cn.xhy.shop.service.back.impl.MemberServiceBackImpl;

import java.util.HashSet;
import java.util.Set;


public class TestMD5 {
    public static String captureName1(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;

    }
    //首字母大写
    public static String captureName2(String name) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;

        try {
            startTime = System.currentTimeMillis();    //获取开始时间
            System.out.println(captureName1("113131"));
            endTime = System.currentTimeMillis();    //获取结束时间
           /* System.out.println("IN程序运行时间：" + (endTime - startTime) + "ms");
            startTime = System.currentTimeMillis();    //获取开始时间
            System.out.println(captureName2(""));
            endTime = System.currentTimeMillis();    //获取结束时间
            System.out.println("IN程序运行时间：" + (endTime - startTime) + "ms");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
