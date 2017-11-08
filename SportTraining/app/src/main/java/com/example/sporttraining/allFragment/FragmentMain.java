package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sporttraining.R;

public class FragmentMain extends Fragment implements View.OnClickListener {

    FragmentTransaction fragmentTransaction;

    Button btn_shedule;
    Button btn_go_muscule;
    Button btn_go_info;
    Button btn_go_motiv;
    Button btn_go_finish;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_main, null);

        InitView(view);

        fragmentTransaction = getFragmentManager().beginTransaction();

        return view;
    }

    private void InitView(View v){
        btn_shedule = (Button) v.findViewById(R.id.button_shedule);
        btn_go_muscule = (Button) v.findViewById(R.id.button_muscule);
        btn_go_info = (Button) v.findViewById(R.id.button_info);
        btn_go_motiv = (Button) v.findViewById(R.id.button_motiv);
        btn_go_finish = (Button) v.findViewById(R.id.button_b_ack);
    }

    @Override
    public void onResume() {
        super.onResume();
        btn_shedule.setOnClickListener(this);
        btn_go_muscule.setOnClickListener(this);
        btn_go_info.setOnClickListener(this);
        btn_go_motiv.setOnClickListener(this);
        btn_go_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_shedule:
                showShedule();
                break;
            case R.id.button_muscule:
                showGroup();
                break;
            case R.id.button_info:
                showInfo();
                break;
            case R.id.button_motiv:
                showMotiv();
                break;
            case R.id.button_b_ack:
                allFinish();
                break;
            default:
                break;
        }
    }

    private void allFinish(){
        getActivity().finish();
    }

    private void showMotiv() {
        FragmentMotiv motiv = new FragmentMotiv();
        fragmentTransaction.replace(R.id.frame_layout_main, motiv);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showShedule(){
        FragmentsDays fragShedule = new FragmentsDays();
        fragmentTransaction.replace(R.id.frame_layout_main, fragShedule);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showGroup(){
        FragmentList fragmentList = new FragmentList();
        fragmentTransaction.replace(R.id.frame_layout_main, fragmentList);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void showInfo(){
        FragmentInfo info = new FragmentInfo();
        fragmentTransaction.replace(R.id.frame_layout_main, info);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
