package com.app.supawit.bcomprojectit.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.supawit.bcomprojectit.R;


public class menuQAFragment extends Fragment {

    private static final int TAKE_PICTURE = 1;
    Uri imageuri ;
    ImageView imageView;
    public menuQAFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_menu_qa, null);
        Bundle bundle  = this.getArguments();
        String a = bundle.getString("Key");
        final TextView test = (TextView) v.findViewById(R.id.txttest);
        test.setText(a.substring(0,4)+"-testQS16030101");
        Button btnc = (Button) v.findViewById(R.id.btncamera);
        Button btnq = (Button) v.findViewById(R.id.btnqa);

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapFragment fragment = new CapFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Key",test.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                //fragTransaction.replace(R.id.fragment_con,fragment);
                fragTransaction.replace(R.id.fragment_con,fragment).addToBackStack(null).commit();
            }
        });


        btnq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment fragment = new MainFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Key",test.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                //fragTransaction.replace(R.id.fragment_con,fragment);
                fragTransaction.replace(R.id.fragment_con,fragment).addToBackStack(null).commit();
            }
        });


       /*btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageuri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);



            }
        });*/



        return v;
    }


}
