package com.mayo.godko.godhouse;


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
public class FragGodHouse extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Monkey monkey;


    public FragGodHouse() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monkey = Monkey.getMonkey();
        monkey.nGodHouses = new ArrayList<>();

        JSONObject data = null;

        switch (monkey.religionSelected){
            case HINDUISM:
                data = Util.getJSONFromAssets(getActivity(), "json/hinduism.json");
                break;
            case ISLAM:
                data = Util.getJSONFromAssets(getActivity(), "json/islam.json");
                break;
            case JAINISM:
                data = Util.getJSONFromAssets(getActivity(), "json/jainism.json");
                break;
            case SIKHISM:
                data = Util.getJSONFromAssets(getActivity(), "json/sikhism.json");
                break;
        }

        try {
            JSONArray homesJSON = data.getJSONArray("god_houses");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<GodHouse>>() {
            }.getType();

            monkey.nGodHouses.clear();
            monkey.nGodHouses.addAll((ArrayList<GodHouse>) gson.fromJson(homesJSON.toString(), listType));
//            Log.i(Tags.LOG, String.valueOf(monkey.nGodHouses));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_godhouse, container, false);
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
        mAdapter = new GodHouseAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(Tags.LOG, "Position clicked" + ((RobotoView) v.findViewById(R.id.name).gett);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

/*        ((GodHouseAdapter) mAdapter).setOnItemClickListener(new GodHouseAdapter.MyClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.i(Tags.LOG, String.valueOf(position));

                Intent intent = new Intent(getActivity(), ActGodHouseDetails.class);
                intent.putExtra(Tags.POSITION, position);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String transitionName = getString(R.string.godhouse_image);
                    getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else
                    getActivity().startActivity(intent);


            }
        });*/
    }
}
