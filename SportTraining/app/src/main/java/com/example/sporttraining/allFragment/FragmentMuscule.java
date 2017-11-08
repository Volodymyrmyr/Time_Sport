package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Володимир on 01.08.2016.
 */
public class FragmentMuscule extends Fragment implements View.OnClickListener {

    View view;
    String namegroup = "";
    String namechildfile = "";

    private final String TAG = "log";


    String allPath = "";

    MyAsyncTask myAsyncTask;

    TextView txtmy;
    Button btnback;
    ImageView imagemuscl;

    String lineImage = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_muscule, null);

        txtmy = (TextView) view.findViewById(R.id.showFileMuscule);
        btnback = (Button) view.findViewById(R.id.button_nazad);
        imagemuscl = (ImageView) view.findViewById(R.id.image_muscule);

        Bundle nbundel = this.getArguments();

        if (nbundel != null) {
            namegroup = nbundel.getString("namemuscule");
            namechildfile = nbundel.getString("musculetask");
            Log.d(TAG, "name is " + namegroup + " and filename is " + namechildfile);
        }

        //знаходимо файл картинку відповідно від вибраного файлу (файл txt і картинки мають однакові назви для простоти)
        lineImage = findImage(namechildfile);
        Log.d(TAG, "hi " + lineImage);
        String pack = getActivity().getPackageName();
        int image_my = 0;
        image_my = getResources().getIdentifier(lineImage, "drawable", pack);
        imagemuscl.setImageResource(image_my);

        allPath = namegroup + "/" + namechildfile;

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(allPath);

        return view;
    }

    private String findImage(String str) {
        String retstr = "";
        retstr = str.substring(0, str.lastIndexOf("."));
        return retstr;
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
            txtmy.append(values[0] + "\n");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        btnback.setOnClickListener(this);
    }
}
