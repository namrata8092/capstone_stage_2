package com.nds.pmc.util;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by Namrata on 11/8/2017.
 */

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isValidString(String stringToValidate) {
        return stringToValidate != null && !TextUtils.isEmpty(stringToValidate.trim());
    }

    public static boolean isValidJSON(JSONObject jsonObject) {
        return jsonObject != null && !TextUtils.isEmpty(jsonObject.toString());
    }
}