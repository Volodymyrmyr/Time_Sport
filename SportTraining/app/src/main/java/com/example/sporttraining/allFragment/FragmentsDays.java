package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sporttraining.R;

/**
 * Created by Володимир on 29.07.2016.
 */
public class FragmentsDays extends Fragment implements View.OnClickListener {

    View view;

    Button btn_monday;
    Button btn_wednesday;
    Button btn_friday;
    Button btn_back;

    private final String KEY_DAY = "day";

    FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentdays, null);

        InitView(view);

        transaction = getFragmentManager().beginTransaction();

        return view;
    }

    private void InitView(View v){
        btn_monday = (Button) v.findViewById(R.id.button_monday);
        btn_wednesday = (Button) v.findViewById(R.id.button_wednesday);
        btn_friday = (Button) v.findViewById(R.id.button_friday);
        btn_back = (Button) v.findViewById(R.id.button_back);

    }

    @Override
    public void onResume() {
        super.onResume();

        btn_monday.setOnClickListener(this);
        btn_wednesday.setOnClickListener(this);
        btn_friday.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_monday:
                btnMondayShow();
                break;
            case R.id.button_wednesday:
                btnWednesdayShow();
                break;
            case R.id.button_friday:
                btnFridayShow();
                break;
            case R.id.button_back:
                btnBackToMain();
                break;
            default:
                break;
        }
    }

    private void btnBackToMain() {
        getActivity().getFragmentManager().popBackStack();
    }

    private void btnFridayShow() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DAY, "friday");

        ShowFiles showFiles = new ShowFiles();
        showFiles.setArguments(bundle);

        transaction.replace(R.id.frame_layout_main, showFiles);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void btnWednesdayShow() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DAY, "wednesday");

        ShowFiles showFiles = new ShowFiles();
        showFiles.setArguments(bundle);

        transaction.replace(R.id.frame_layout_main, showFiles);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void btnMondayShow(){
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DAY, "monday");

        ShowFiles showFiles = new ShowFiles();
        showFiles.setArguments(bundle);

        transaction.replace(R.id.frame_layout_main, showFiles);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
