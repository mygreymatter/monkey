package com.mayo.godko;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by mayo on 2/9/15.
 */
public class NaviagtionAdapter extends BaseAdapter {
    private String[] menu = new String[]{"GodHouses","Priests","Shopping"};
    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public String getItem(int position) {
        return menu[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RobotoView v = (RobotoView) LayoutInflater.from(parent.getContext()).inflate(R.layout.r_navigation_drawer,parent,false);
        v.setText(getItem(position));
        return v;
    }
}
