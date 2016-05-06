package com.app.supawit.bcomprojectit.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.ConnectionSQL;
import com.app.supawit.bcomprojectit.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class warehouseFragment extends Fragment {

    private ListView lv;

    ArrayAdapter<String> adapter;
    int check = 1;
    EditText inputSearch;
    String area;
    ConnectionSQL connectionSQL;
    ArrayList<String> list;
    Statement stmt = null;
    ResultSet rs = null;

    String abbname;
    public warehouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_warehouse,null);

        Bundle bundle  = this.getArguments();
        area = bundle.getString("Key");


        try {
            connectionSQL = new ConnectionSQL();
            Connection con = connectionSQL.CONN();
            stmt = con.createStatement();
            String query =
                    "select *from Tmp_whcode";

            rs = stmt.executeQuery(query);

            list = new ArrayList<>();

            while(rs.next()) {
                abbname = rs.getString("ABBNAME");
                String whcode = rs.getString("WHCODE");
                list.add(whcode + ":" + abbname);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        /*String products[] = {"1001 : CENL", "1002 : MBKC", "1006 : CENS", "1007 : MALG", "1008 : LOTJ",
                "1009 : LOTB", "1010 : MALR",
                "1011 : MALT", "1012 : CENP", "1013 : LOTL", "1014 : CJUR","5066 : BCASW"
                ,"5013 : BCBCB","5048 : BCBCT","5039 : BCBHM","5057 : BCBKB","5030 : BCBNS","5016 : BCBRD"
                ,"5045 : BCBSP","7001 : BMSCSN","7002 : BMFPR","7003 : BMCCM","7004 : BMFSI","7005 : BMCSM"};*/


        //list = new ArrayList<String>();
       // for (int i = 0; i < abbname.length(); ++i) {
           // list.add(abbname);
        //}

        lv = (ListView) v.findViewById(R.id.list_view);
        inputSearch = (EditText) v.findViewById(R.id.inputsearch);

        // Adding items to listview
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.product_name, list);
        //StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),R.layout.list_item,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ItemList());

        /**
         * Enabling Search Filter
         * **/
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                warehouseFragment.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

        });

        return v;
    }

    class ItemList implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ViewGroup vg = (ViewGroup) view;
            TextView txt = (TextView) vg.findViewById(R.id.product_name);
            Toast.makeText(getActivity(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

            menuQAFragment fragment = new menuQAFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Key",txt.getText().toString());
            bundle.putString("area",area);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
            //fragTransaction.replace(R.id.fragment_con,fragment);
            fragTransaction.replace(R.id.fragment_con,fragment).addToBackStack(null).commit();



        }



    }

}
