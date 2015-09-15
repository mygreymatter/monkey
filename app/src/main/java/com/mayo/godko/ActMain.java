package com.mayo.godko;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mayo.godko.godhouse.FragGodHouse;
import com.mayo.godko.priest.FragPriests;
import com.mayo.godko.shop.FragShop;

import java.lang.reflect.Field;


public class ActMain extends AppCompatActivity implements Callbacks.MenuIconListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private FragmentManager fm;
    private int mCurrentPosition;
    private TextView mToolbarTitle;
    private Monkey mMonkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);
        mMonkey = Monkey.getMonkey();

        nitView();
        if (toolbar != null) {
            toolbar.setTitle("Godko");
            try {
                Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
                f.setAccessible(true);
                mToolbarTitle = (TextView) f.get(toolbar);
                mToolbarTitle.setTypeface(mMonkey.getRoboto(this, "medium"));
                mToolbarTitle.setTextColor(AndroidUtil.getColor(this, android.R.color.white));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            setSupportActionBar(toolbar);
        }
        initDrawer();

        fm = getFragmentManager();
        fm.beginTransaction().add(R.id.container, new FragGodHouse()).commit();
        mCurrentPosition = 0;

//        Log.i(TAG, String.valueOf(Util.getJSONFromAssets(this, "json/hinduism.json")));
    }

    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        leftDrawerList.setAdapter(new NaviagtionAdapter());

        leftDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCurrentPosition != position)
                    switch (position) {
                        case 0:
                            Log.i(Tags.LOG, "Position: " + 0);
                            fm.beginTransaction().replace(R.id.container, new FragGodHouse(),"godhouse").commit();
                            mCurrentPosition = 0;
                            break;
                        case 1:
                            Log.i(Tags.LOG, "Position: " + 1);
                            fm.beginTransaction().replace(R.id.container, new FragPriests(),"priests").commit();
                            mCurrentPosition = 1;
                            break;
                        case 2:
                            Log.i(Tags.LOG, "Position: " + 2);
                            fm.beginTransaction().replace(R.id.container, new FragShop(),"shopping").commit();
                            mCurrentPosition = 2;
                            break;

                    }
                drawerLayout.closeDrawer(leftDrawerList);
            }
        });

    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenuIcon(int id) {
        Fragment frag = getFragmentManager().findFragmentByTag("shopping");
        if(null != frag)
            ((FragShop)frag).setCart(id);
    }
}