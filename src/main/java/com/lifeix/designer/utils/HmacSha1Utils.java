package com.lifeix.designer.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by neoyin on 2017/1/16.
 */
public class HmacSha1Utils {

    private static final String HMACSHA1 = "HMACSHA1";

    /**
     * 计算HMACSHA1签名
     *
     * @param input
     * @param secretKey
     * @return
     */
    public static String hmacSha1(String input, String secretKey) {
        Mac mac = null;
        try {
            String keyString = URLEncoder.encode(secretKey,"UTF-8");

            mac = Mac.getInstance(HMACSHA1);
            Key key = new SecretKeySpec(keyString.getBytes("UTF-8"), HMACSHA1);
            mac.init(key);

            byte[] hashValue = mac.doFinal(input.getBytes("UTF-8"));
            String encodedHashValue = new String(Base64.encodeBase64(hashValue));

            return encodedHashValue;

        } catch (NoSuchAlgorithmException e) {

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public static String sign(String appKey, String imgUrl, String nickname, String profileUrl, String isvUserId){
        StringBuilder sb = new StringBuilder();
        sb.append("img_url=" + imgUrl);
        sb.append("&");
        sb.append("nickname=" + nickname);
        sb.append("&");
        sb.append("profile_url=" + profileUrl);
        sb.append("&");
        sb.append("user_id=" + isvUserId);
        String sign = HmacSha1Utils.hmacSha1(sb.toString(), appKey);
        return sign;
    }

//    public static void main(String[] args){
//        System.out.println(sign("16bd0e2533092b96e3e55958ef19d08a", "img", "nick", "profileUrl", "isvUserId"));
//    }
}
