package com.mayo.godko;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * Created by mayo on 3/9/15.
 */
public class AndroidUtil {

    public static final int getColor(@NonNull Context context,int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return ContextCompat.getColor(context,id);
        return context.getResources().getColor(id);
    }

    public static final Drawable getDrawable(@NonNull Context context,int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return ContextCompat.getDrawable(context, id);
        return context.getResources().getDrawable(id);
    }

}
