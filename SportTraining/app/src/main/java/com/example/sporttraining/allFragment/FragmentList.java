package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.example.sporttraining.R;
import com.example.sporttraining.others.AdapterHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Володимир on 01.08.2016.
 */
public class FragmentList extends Fragment implements View.OnClickListener{

    View view;

    ExpandableListView expandableListView;
    AdapterHelper adapterHelper;
    SimpleExpandableListAdapter listAdapter;

    private final String TAG = "log";

    private final String NAME_MUSCULE = "namemuscule";
    private final String MUSCULE_TASK = "musculetask";

    String dirnamegroup = "";
    String filenamegroup = "";

    FragmentTransaction transaction;

    Button btnmyback;

    AssetManager assetManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, null);

        adapterHelper = new AdapterHelper(getActivity());
        listAdapter = adapterHelper.getAdapter();

        assetManager = getActivity().getAssets();


        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableList_group);
        expandableListView.setAdapter(listAdapter);

        btnmyback = (Button) view.findViewById(R.id.buttonbck);

        transaction = getFragmentManager().beginTransaction();

        return view;
    }

    OnChildClickListener listener = new OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            dirnamegroup = adapterHelper.numberGroup[groupPosition];
            String prom = cmpValue(dirnamegroup);
            dirnamegroup = prom;
            // public String[] numberGroup = new String[]{"chest", "hand", "back", "shoulders", "legs"};
            if(prom.equals(adapterHelper.numberGroup[0])){
                String g = adapterHelper.numberOfChest[childPosition];
                filenamegroup = dispFiles(assetManager, prom, g);
                goToMuscule(dirnamegroup, filenamegroup);
            }
            if(prom.equals(adapterHelper.numberGroup[1])){
                String g = adapterHelper.numberOfHand[childPosition];
                filenamegroup = dispFiles(assetManager, prom, g);
                goToMuscule(dirnamegroup, filenamegroup);
            }
            if(prom.equals(adapterHelper.numberGroup[2])){
                String g = adapterHelper.numberOfBack[childPosition];
                filenamegroup = dispFiles(assetManager, prom, g);
                goToMuscule(dirnamegroup, filenamegroup);
            }
            if(prom.equals(adapterHelper.numberGroup[3])){
                String g = adapterHelper.numberOfShoulders[childPosition];
                filenamegroup = dispFiles(assetManager, prom, g);
                goToMuscule(dirnamegroup, filenamegroup);
            }
            if(prom.equals(adapterHelper.numberGroup[4])){
                String g = adapterHelper.numberOfLegs[childPosition];
                filenamegroup = dispFiles(assetManager, prom, g);
                goToMuscule(dirnamegroup, filenamegroup);
            }

//            Log.d(TAG, "you select child" + dirnamegroup);
            Log.d(TAG, "you select childPos" + filenamegroup);

            return false;
        }
    };
    private String cmpValue(String f){
        String str = "";
        for(String  sr : adapterHelper.numberGroup)
        {
            if(sr.equals(f)){
                Log.d(TAG, sr);
                str = sr;
                break;
            }
        }
        Log.d(TAG, str);
        return str;
    }


    private void goToMuscule(String name, String secName){
        Bundle bundle = new Bundle();
        bundle.putString(NAME_MUSCULE, name);
        bundle.putString(MUSCULE_TASK, secName);

        FragmentMuscule muscule = new FragmentMuscule();
        muscule.setArguments(bundle);

        transaction.replace(R.id.frame_layout_main, muscule);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    @Override
    public void onResume() {
        super.onResume();

        expandableListView.setOnChildClickListener(listener);
        btnmyback.setOnClickListener(this);
    }


    private String dispFiles(AssetManager manager, String path, String fname){
        String value = "";
        try{
            String[] list = manager.list(path);
            if(list != null){
                for(int i = 0; i < list.length; i++){
                    //Log.d(TAG, "assets package - " + path + "/" + list[i]);
                    if(fname.equals(list[i]))
                    {
                        value = fname;
                        //return value;
                    }
                }
            }
        }
        catch (IOException exc){
            Log.d(TAG,"errorjdfjhksf");
        }
        return value;
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().popBackStack();
    }
}
