package com.app.supawit.bcomprojectit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.supawit.bcomprojectit.Fragment.warehouseFragment;


public class HOME extends Fragment implements View.OnClickListener  {

    Button chkbtn,hisbtn,rptbtn,mapbtn;

    public static HOME newInstance() {
        HOME fragment = new HOME();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, null);
        chkbtn = (Button) v.findViewById(R.id.button1);
        hisbtn = (Button) v.findViewById(R.id.button3);
        rptbtn = (Button) v.findViewById(R.id.button2);
        mapbtn = (Button) v.findViewById(R.id.button4);



        chkbtn.setOnClickListener(this);




        return v;
    }


    @Override
    public void onClick(View v) {
        if (v == chkbtn){
            //เปิดหน้า fragment
            warehouseFragment fragment = new warehouseFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
            //fragTransaction.replace(R.id.fragment_con,fragment);
            fragTransaction.replace(R.id.fragment_con,fragment).addToBackStack("tag").commit();

        }
    }


}
