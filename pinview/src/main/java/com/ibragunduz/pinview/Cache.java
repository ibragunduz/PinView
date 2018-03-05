package com.ibragunduz.pinview;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by ibrahim on 5.03.2018.
 */

public class Cache {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public static Typeface getTypeface
            (String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }





}
