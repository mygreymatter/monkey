package com.mayo.godko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class ActReligions extends AppCompatActivity {

    private Monkey monkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_religions);

        monkey = Monkey.getMonkey();
        
    }

    public void showHinduism(View v){
        Log.i(Tags.LOG, "Hinduism");
        monkey.setReligion(Monkey.Religion.HINDUISM);
        startGodko();
    }
    public void showIslam(View v){
        Log.i(Tags.LOG,"Islam");
        monkey.setReligion(Monkey.Religion.ISLAM);
        startGodko();
    }

    public void showJainism(View v){
        Log.i(Tags.LOG,"Jainism");
        monkey.setReligion(Monkey.Religion.JAINISM);
        startGodko();
    }

    public void showSikhism(View v){
        Log.i(Tags.LOG,"Sikhism");
        monkey.setReligion(Monkey.Religion.SIKHISM);
        startGodko();
    }

    private void startGodko(){
        Intent intent = new Intent(this,ActMain.class);
        startActivity(intent);
    }

}
