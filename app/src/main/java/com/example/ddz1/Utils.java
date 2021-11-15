package com.example.ddz1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Utils {

    public static boolean isNullOrEmptyString(String s) {
        return s == null || s.isEmpty();
    }

    public static Bitmap decodeBase64(String photo) {
        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0 , decodedString.length);
    }
}
