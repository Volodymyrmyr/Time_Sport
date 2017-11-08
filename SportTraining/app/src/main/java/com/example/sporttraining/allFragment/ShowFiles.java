package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sporttraining.MainActivity;
import com.example.sporttraining.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Володимир on 29.07.2016.
 */
public class ShowFiles extends Fragment implements View.OnClickListener {

    private final String TAG = "log";

    View view;

    TextView txtallshow;
    String dayOfWeek = "";

    Button back_button;

    MyAsyncTask myAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.showfiles, null);

        Bundle mybundle = this.getArguments();

        if (mybundle != null) {
            dayOfWeek = mybundle.getString("day");
            dayOfWeek = dayOfWeek + ".txt";
            Log.d(TAG, "day of week is " + dayOfWeek);
        }

        InitView(view);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(dayOfWeek);

        return view;
    }

    private void InitView(View v) {
        txtallshow = (TextView) v.findViewById(R.id.text_show_all);
        back_button = (Button) v.findViewById(R.id.backtoback);
        txtallshow.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onResume() {
        super.onResume();
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().popBackStack();
    }

    class MyAsyncTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String[] files;
            String line = "";

            AssetManager myAssestManager = getActivity().getAssets();
            try {
                InputStream inputStream = myAssestManager.open(params[0]);
                Log.d(TAG, "params 0 = " + params[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    publishProgress(line);
                }
                Log.d(TAG, "all is good");

            } catch (IOException exc) {
                Log.d(TAG, "error to IO");
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtallshow.append(" " + values[0] + "\n");
        }
    }
}
