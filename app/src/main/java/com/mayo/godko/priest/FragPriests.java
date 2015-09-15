package com.mayo.godko.priest;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mayo.godko.Monkey;
import com.mayo.godko.R;
import com.mayo.godko.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragPriests extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Monkey monkey;


    public FragPriests() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monkey = Monkey.getMonkey();
        monkey.nPriests = new ArrayList<>();

        JSONObject data = Util.getJSONFromAssets(getActivity(), "json/hinduism.json");
        try {
            JSONArray homesJSON = data.getJSONArray("priests");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Priest>>() {
            }.getType();

            monkey.nPriests.clear();
            monkey.nPriests.addAll((ArrayList<Priest>) gson.fromJson(homesJSON.toString(), listType));
//            Log.i(Tags.LOG, String.valueOf(monkey.nPriests));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_priest, container, false);
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
        mAdapter = new PriestsAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

}
