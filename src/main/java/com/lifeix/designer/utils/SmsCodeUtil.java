package com.lifeix.designer.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by neoyin on 2017/1/16.
 */
public class SmsCodeUtil {

    public static String[] BEFORESHUFFLE = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public final static String generateSmsCode() {
        List list = Arrays.asList(BEFORESHUFFLE);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();

        for(int afterShuffle = 0; afterShuffle < list.size(); ++afterShuffle) {
            sb.append((String)list.get(afterShuffle));
        }

        String var4 = sb.toString();
        String result = var4.substring(1, 7);
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            System.out.println(generateSmsCode());
        }
    }

}
