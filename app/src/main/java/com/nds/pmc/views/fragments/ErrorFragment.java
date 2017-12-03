package com.nds.pmc.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nds.pmc.R;
import com.nds.pmc.common.Constants;

/**
 * Created by Namrata on 11/22/2017.
 */

public class ErrorFragment extends Fragment {

    String mErrorMsg;
    public static ErrorFragment newInstance(String error){
        ErrorFragment errorFragment = new ErrorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ERROR_MSG_KEY, error);
        errorFragment.setArguments(bundle);
        return errorFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            Bundle bundle = getArguments();
            mErrorMsg = bundle.getString(Constants.ERROR_MSG_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_error, container, false );
        TextView errorMsg = (TextView)rootView.findViewById(R.id.errorMsg);
        errorMsg.setText(mErrorMsg);
        return rootView;
    }
}
