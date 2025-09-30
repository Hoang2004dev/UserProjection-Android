package com.example.userprotection.utils;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

public class JwtUtils {

    // Lấy phần payload của JWT
    private static String getPayload(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;

            String payload = parts[1];
            byte[] decodedBytes = Base64.decode(payload, Base64.URL_SAFE | Base64.NO_WRAP);
            return new String(decodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy email từ JWT
    public static String getEmail(String token) {
        try {
            String payload = getPayload(token);
            if (payload == null) return null;

            JSONObject json = new JSONObject(payload);
            return json.optString("email", null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy tenantId từ JWT
    public static String getTenantId(String token) {
        try {
            String payload = getPayload(token);
            if (payload == null) return null;

            JSONObject json = new JSONObject(payload);
            return json.optString("tenantId", null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
