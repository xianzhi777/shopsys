package com.woniu.utils;

public class Utils {
    //检测字符串是否符合规范(以字母开头,长度6-10)
    public static boolean ckeckString(String str){
        String regex ="[a-zA-Z][0-9A-Za-z]{5,9}";
        return str.matches(regex);
    }
    //字母数字,长度6-12
    public static boolean checkStringLength(String str){
        String regex ="[a-zA-Z0-9]{5,11}";
        return str.matches(regex);
    }
}
