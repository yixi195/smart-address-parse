package com.ysl.smart;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YangShlai on 2020/3/24.
 */
public class ConverUtils {

    public static String toString(InputStream is) {
        return toString(is, "utf-8");
    }

    public static String toString(InputStream is, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    is.close();
                    break;
                }

                sb.append(line).append("\n");
            }
        } catch (IOException var5) {
            Log.e("ysl",var5.getMessage());
        }
        return sb.toString();
    }

    /**
     * 正则表达式字符串替换
     * @param content 字符串
     * @param pattern 正则表达式
     * @param newString 新的替换字符串
     * @return 返回替换后的字符串
     */
    public static String regReplace(String content,String pattern,String newString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        String result = m.replaceAll(newString);
        return result;
    }

    /**
     * 是否为合法手机号
     * @param number
     * @return
     */
    public static boolean isPhoneNumber(String number) {
        String telRegex = "(86-[1][0-9]{10}) | (86[1][0-9]{10})|([1][0-9]{10})";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        String telRegex = "[1][1234567890]\\d{9}";
        if (TextUtils.isEmpty(number))
            return false;
        else
            return number.matches(telRegex);
    }

    /**
     * 判断是否为空
     * @param content
     * @return
     */
    public static boolean isEmpty(String content){
        if ("null".equals(content) || TextUtils.isEmpty(content)) return true;
        return false;
    }
}
