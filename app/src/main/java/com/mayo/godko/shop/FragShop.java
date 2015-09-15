package com.mayo.godko.shop;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mayo.godko.AndroidUtil;
import com.mayo.godko.Monkey;
import com.mayo.godko.R;
import com.mayo.godko.Tags;
import com.mayo.godko.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragShop extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Monkey monkey;
    private Menu mMenu;

    public FragShop() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monkey = Monkey.getMonkey();
        monkey.nShopping = new ArrayList<>();

        JSONObject data = Util.getJSONFromAssets(getActivity(), "json/hinduism.json");
        try {
            JSONArray homesJSON = data.getJSONArray("shopping");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Shop>>() {
            }.getType();

            monkey.nShopping.clear();
            monkey.nShopping.addAll((ArrayList<Shop>) gson.fromJson(homesJSON.toString(), listType));
//            Log.i(Tags.LOG, String.valueOf(monkey.nShopping));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ShopAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Order done!",Toast.LENGTH_SHORT).show();
                monkey.nShopped.clear();
                for(Shop shop : monkey.nShopping)
                    shop.quantity = 0;

                setCart(0);
                mAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        mMenu = menu;
        inflater.inflate(R.menu.m_frag_shopping, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void setCart(int id) {
        Log.i(Tags.LOG, "Set Cart");
        if (id == 1)
            mMenu.getItem(1).setIcon(AndroidUtil.getDrawable(getActivity(),R.drawable.ic_shopping_cart_full));
        else
            mMenu.getItem(1).setIcon(AndroidUtil.getDrawable(getActivity(), R.drawable.ic_shopping_cart_empty));
    }

}
