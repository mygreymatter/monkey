package com.mayo.godko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActGodHouseDetails extends AppCompatActivity {

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_godhouse_details);
        mPosition = getIntent().getIntExtra(Tags.POSITION,-1);
        setViews();
    }

    private void setViews() {
        ImageView image = (ImageView) findViewById(R.id.image);

        int resource = -1;

        switch (mPosition){
            case 0: resource = R.drawable.monkey_1; break;
            case 1: resource = R.drawable.monkey_2; break;
            case 2: resource = R.drawable.monkey_3; break;
            case 3: resource = R.drawable.monkey_4; break;
        }

        image.setImageResource(resource);

        ((RobotoView)findViewById(R.id.name)).setText(Monkey.getMonkey().nGodHouses.get(mPosition).name);
        ((RobotoView)findViewById(R.id.god)).setText(Monkey.getMonkey().nGodHouses.get(mPosition).god_of_house);
    }

    public void onbackPressed(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
