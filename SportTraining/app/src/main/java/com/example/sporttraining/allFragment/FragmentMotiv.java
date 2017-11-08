package com.example.sporttraining.allFragment;

import android.app.Fragment;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sporttraining.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Володимир on 04.08.2016.
 */
public class FragmentMotiv extends Fragment implements View.OnClickListener{

    private final String TAG = "log";
    private final String FILE_NAME = "motiv.txt";
    View view;
    TextView txt_motiv;

    MyAsyncTask myAsyncTask;
    ImageView imageView;

    Button btnback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_motiv, null);

        txt_motiv = (TextView) view.findViewById(R.id.textView_motivation);
        btnback = (Button) view.findViewById(R.id.backbutton);
        imageView = (ImageView) view.findViewById(R.id.imageView_motiv);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(FILE_NAME);

        imageView.setImageResource(R.drawable.tiv);

        return view;
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();
        btnback.setOnClickListener(this);
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
            txt_motiv.append(values[0] + "\n");
        }
    }
}
