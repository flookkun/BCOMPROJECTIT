package com.app.supawit.bcomprojectit.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.supawit.bcomprojectit.ConnectionSQL;
import com.app.supawit.bcomprojectit.NewsItem;
import com.app.supawit.bcomprojectit.R;
import com.app.supawit.bcomprojectit.View.CustomAdepter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    Integer check = 0;
    ConnectionSQL connectionSQL;
    ArrayList<String> list1,list2,list3,list4,list5;
    Statement stmt = null;
    ResultSet rs = null;
    String warea,doc,abbname,area,docdate,point;
    ListView lv8;
    String[] docarray,abbnamearray,areaarray,docdatearray,pointarray;

    private ArrayList listData;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, null);


        Bundle bundle  = this.getArguments();
        warea = bundle.getString("Key");

        ///Query///

        try {
            connectionSQL = new ConnectionSQL();
            Connection con = connectionSQL.CONN();
            stmt = con.createStatement();
            String query =

                    "select * from MAS_PJ_REPORT where arcode = '"+warea+"'";

            rs = stmt.executeQuery(query);
            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            list3 = new ArrayList<>();
            list4 = new ArrayList<>();
            list5 = new ArrayList<>();

            while(rs.next()) {
                abbname = rs.getString("ABBNAME");
                doc = rs.getString(2);
                area = rs.getString("ARCODE");
                docdate = rs.getString("DOC_DATE");
                point = rs.getString("TOTAL_POINT");
                list1.add(abbname);
                list2.add(doc);
                list3.add(area);
                list4.add(docdate);
                list5.add(point);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*list2 = new ArrayList<String>();
         for (int i = 0; i < doc.length(); ++i) {
         list2.add(doc);
        }*/


        docarray = list2.toArray(new String[list2.size()]);
        abbnamearray = list1.toArray(new String[list1.size()]);
        areaarray = list3.toArray(new String[list3.size()]);
        docdatearray = list4.toArray(new String[list4.size()]);
        pointarray = list5.toArray(new String[list5.size()]);


        ////////////////////////////////////////////////////////////
        ArrayList data = getListData();
        CustomAdepter adapter = new CustomAdepter(getContext(),data);
        lv8 = (ListView) v.findViewById(R.id.lv8);
        lv8.setAdapter(adapter);
        lv8.setOnItemClickListener(new ItemList());
        //Toast.makeText(getActivity(),warea,Toast.LENGTH_LONG).show();


        return v;
    }

    public ArrayList getListData() {

        ArrayList<NewsItem> results = new ArrayList<NewsItem>();
        NewsItem newsData;


        for (int i = 0 ; i < docarray.length ; i++)
        {
            newsData = new NewsItem();
            newsData.setHeadline(docarray[i]);
            newsData.setDocdate(docdatearray[i]);
            newsData.setScore(pointarray[i]);
            newsData.setWhName(abbnamearray[i]);
            results.add(newsData);
        }



        return results;
    }

    private class ItemList implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ViewGroup vg = (ViewGroup) view;
            TextView txt = (TextView) vg.findViewById(R.id.doc_number);
            //Toast.makeText(getActivity(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

            MainFragment fragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("chk",check);
            bundle.putString("Key",txt.getText().toString());
            bundle.putString("area",warea);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
            //fragTransaction.replace(R.id.fragment_con,fragment);
            fragTransaction.replace(R.id.fragment_con,fragment).addToBackStack(null).commit();
        }
    }
}
