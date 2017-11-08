package com.example.sporttraining.allFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sporttraining.R;

/**
 * Created by Володимир on 03.08.2016.
 */
public class FragmentInfo extends Fragment implements View.OnClickListener {
    View view;

    Button btnmy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, null);

        btnmy = (Button) view.findViewById(R.id.backbutt);
        return view;
    }

    @Override
    public void onClick(View v) {
        getActivity().getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();
        btnmy.setOnClickListener(this);
    }
}
