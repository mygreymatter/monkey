package com.mayo.godko;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoView extends TextView {

    private final String textStyle;

    public RobotoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        textStyle = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textStyle");

        init();
    }

    public void init() {

        if (textStyle == null)
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/roboto_light.ttf"));
        else if(textStyle.contains("bold"))
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/roboto_bold.ttf"));
        else if(textStyle.contains("italic"))
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/roboto_thin_italic.ttf"));
    }
}
