package com.baiwang.einvoice.qz.utils.token;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.sql.Timestamp;

public class TokenHelper {

    public static String generateToken(String taxNumber, String tokenKey) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String timeValue = String.valueOf(now.getTime());
        String signature = generateSignature(taxNumber, timeValue, tokenKey);
        return encodeToken(new String[] { taxNumber, timeValue, signature });
    }

    public static String generateSignature(String code, String time, String tokenKey) {
        String timeValue = String.valueOf(time);
        String data = code + timeValue + tokenKey;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] tokenMessage = messageDigest.digest(data.getBytes("UTF-8"));
            return new Base64().encodeAsString(tokenMessage);
        } catch (Exception e) {
            throw new RuntimeException("Can not encode token", e);
        }
    }

    private static String encodeToken(String[] values) {
        StringBuilder sb = new StringBuilder();

        for (int value = 0; value < values.length; ++value) {
            sb.append(values[value]);
            if (value < values.length - 1) {
                sb.append(":");
            }
        }

        String var4 = sb.toString();
        sb = new StringBuilder(new Base64().encodeAsString(var4.getBytes()));
        while (sb.charAt(sb.length() - 1) == 61) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
