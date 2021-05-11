package com.deepit.magicdesign.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.deepit.magicdesign.MagicDesignApplication;
import com.deepit.magicdesign.model.User;
import com.deepit.magicdesign.model.UserRecord;
import com.google.gson.Gson;

import static com.deepit.magicdesign.Constant.USER;

public class MySharedPref {

    private static final String SHARED_PREFS_NAME = "Magic_Design";


    static SharedPreferences sp;
    private static MySharedPref instance;

    private SharedPreferences sharedPreferences;

    private MySharedPref() {
        instance = this;
        sharedPreferences = MagicDesignApplication.getInstance().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MySharedPref getInstance() {
        if (instance == null) {
            instance = new MySharedPref();
        }

        return instance;
    }

    public static void saveData(Context context, String key, String value)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static void saveUser(Context context, String key, UserRecord value)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.commit();
    }
    public static UserRecord getUser(Context context, String key, String value)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString(key, value);
        return gson.fromJson(json, UserRecord.class);
     }
    public static String getData(Context context, String key, String value)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        return sp.getString(key,value);
    }
    public static void DeleteData(Context context)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    public static void NullData(Context context ,String key)
    {
        sp = context.getSharedPreferences(SHARED_PREFS_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,null);
        editor.commit();
    }




    public void delete(String key) {
        if (sharedPreferences.contains(key)) {
            getEditor().remove(key).commit();
        }
    }

    public void save(String key, Object value) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-supported preference");
        }

        editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}
