package com.mayo.godko;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.ArrayMap;

import com.mayo.godko.godhouse.GodHouse;
import com.mayo.godko.priest.Priest;
import com.mayo.godko.shop.Shop;

import java.util.ArrayList;

/**
 * Created by mayo on 2/9/15.
 */
public class Monkey {
    private static Monkey monkey = null;

    private Monkey() {
    }

    public static synchronized Monkey getMonkey() {
        synchronized (Monkey.class) {
            if (null == monkey)
                monkey = new Monkey();
        }
        return monkey;
    }

    public ArrayList<GodHouse> nGodHouses;
    public ArrayList<Priest> nPriests;
    public ArrayList<Shop> nShopping;
    public ArrayMap<String, Integer> nShopped;

    public Typeface getRoboto(Context context, String textStyle) {
        if (textStyle.equals("bold"))
            return Typeface.createFromAsset(context.getAssets(), "fonts/roboto/roboto_bold.ttf");
        else if (textStyle.equals("medium"))
            return Typeface.createFromAsset(context.getAssets(), "fonts/roboto/roboto_medium.ttf");
        else
            return Typeface.createFromAsset(context.getAssets(), "fonts/roboto/roboto_thin.ttf");
    }

    public enum Religion {HINDUISM, ISLAM, JAINISM, SIKHISM}
    public Religion religionSelected = Religion.HINDUISM;

    public void setReligion(Religion religion) {
        switch (religion) {
            case HINDUISM:
                religionSelected = Religion.HINDUISM;
                break;
            case ISLAM:
                religionSelected = Religion.ISLAM;
                break;
            case JAINISM:
                religionSelected = Religion.JAINISM;
                break;
            case SIKHISM:
                religionSelected = Religion.SIKHISM;
                break;
        }

    }
}
