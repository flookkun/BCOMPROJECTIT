package com.app.supawit.bcomprojectit.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.Expantable.MyExpandableListAdapter;
import com.app.supawit.bcomprojectit.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{


    ExpandableListView expListView;
    List<String> groupList;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        createGroupList();
        //createCollection();
        Bundle bundle  = this.getArguments();

        View v = inflater.inflate(R.layout.fragment_main,null);

       /* mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CrimeExpandableAdapter crimeExpandableAdapter = new CrimeExpandableAdapter(getActivity(), generateCrimes());
        crimeExpandableAdapter.onRestoreInstanceState(savedInstanceState);

        mCrimeRecyclerView.setAdapter(crimeExpandableAdapter);*/

        expListView = (ExpandableListView) v.findViewById(R.id.ep_list);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(),groupList,null);
        expListView.setAdapter(adapter);


        TextView ax = (TextView) v.findViewById(R.id.txttest1);
        String a = bundle.getString("Key");
        ax.setText(a);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show();
                //เปิดหน้า fragment
                //getFragmentManager().executePendingTransactions();
                getFragmentManager().popBackStack();
            }
        });




        return  v;
    }

   /*private List<ParentListItem> generateCrimes() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        List<ParentListItem> parentListItems = new ArrayList<>();
        for (Crime crime : crimes) {
            List<CrimeChild> childItemList = new ArrayList<>();
            childItemList.add(new CrimeChild(crime.getDate(), crime.isSolved()));
            crime.setChildItemList(childItemList);
            parentListItems.add(crime);
        }
        return parentListItems;
    }*/

    private void createGroupList() {

        String a[] = {"1","2","3","4","5","6","7","8","9","10"};
        groupList = new ArrayList<String>();
        for (int i = 0 ; i < 10 ; i++) {
            groupList.add(a[i]+".หัวข้อ");
        }
        /*groupList.add("1");
        groupList.add("2");
        groupList.add("3");
        groupList.add("4");
        groupList.add("5");
        groupList.add("6");*/
    }

    /*private void updateDisplay() {
        GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

        dt1.setText(sdf.format(c.getTime()));

        sdf = new SimpleDateFormat("yyyy-MM-dd");

        transDateString = sdf.format(c.getTime());
    }*/


    /*    private void createCollection() {

            String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540",
                    "HP Envy 4-1025TX" };
            String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
            String[] lenovoModels = { "IdeaPad Z Series", "Essential G Series",
                    "ThinkPad X Series", "Ideapad Z Series" };
            String[] sonyModels = { "VAIO E Series", "VAIO Z Series",
                    "VAIO S Series", "VAIO YB Series" };
            String[] dellModels = { "Inspiron", "Vostro", "XPS" };
            String[] samsungModels = { "NP Series", "Series 5", "SF Series" };

            Collection = new LinkedHashMap<String, List<String>>();

            for (String laptop : groupList) {
                if (laptop.equals("HP")) {
                    loadChild(hpModels);
                } else if (laptop.equals("Dell"))
                    loadChild(dellModels);
                else if (laptop.equals("Sony"))
                    loadChild(sonyModels);
                else if (laptop.equals("HCL"))
                    loadChild(hclModels);
                else if (laptop.equals("Samsung"))
                    loadChild(samsungModels);
                else
                    loadChild(lenovoModels);

                Collection.put(laptop, childList);
            }
        }

        private void loadChild(String[] laptopModels) {
            childList = new ArrayList<String>();
            for (String model : laptopModels)
                childList.add(model);
        }
    */











    /*public void createData() //วน for ใส่ค่า
    {
        /*for (int j = 0; j < g.length; j++) {
            EGroup group = new EGroup("1");
            for (int i = 0; i < test.length; i++) {
                group.children.add(test[i]);
            }
            groups.append(j, group);
        }
    }*/

}
