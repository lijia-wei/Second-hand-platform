package com.jess.secondarymarket.util;

/**
 * @author Jess
 * @date 2020/7/31 14:07
 */
public class RandomUtil {
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }
}
