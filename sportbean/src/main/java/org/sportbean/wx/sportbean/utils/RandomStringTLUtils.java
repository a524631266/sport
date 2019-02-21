package org.sportbean.wx.sportbean.utils;

public class RandomStringTLUtils {
    public static String createRandomNum(int num) {
        String randomNumStr = "";
        for (int i = 0; i < num; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }

    public static void main(String[] args) {
        System.out.println(RandomStringTLUtils.createRandomNum(6));
    }
}