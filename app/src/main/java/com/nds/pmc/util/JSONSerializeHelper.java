package com.nds.pmc.util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Created by Namrata on 11/13/2017.
 */

public final class JSONSerializeHelper {
    public JSONSerializeHelper(){}

    public static <T> T deserializeObject(Class<T> objectClass, String json){
        try{
            Gson gson = new Gson();
            return gson.fromJson(json, objectClass);
        }catch(JsonParseException jsonParseException){
            return null;
        }
    }
}
