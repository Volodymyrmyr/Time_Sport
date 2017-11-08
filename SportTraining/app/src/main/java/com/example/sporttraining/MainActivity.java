package com.example.sporttraining;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import com.example.sporttraining.allFragment.FragmentMain;

public class MainActivity extends Activity {

    FragmentTransaction transaction;

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            context = MainActivity.this;

            transaction = getFragmentManager().beginTransaction();
            FragmentMain fragmentMain = new FragmentMain();
            transaction.add(R.id.frame_layout_main, fragmentMain);
            transaction.commit();
        }
    }
}
