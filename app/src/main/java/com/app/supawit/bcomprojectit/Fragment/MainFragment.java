package com.app.supawit.bcomprojectit.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.R;
import com.app.supawit.bcomprojectit.View.CustomViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{


    ExpandableListView expListView;
    List<String> groupList;
    CustomViewGroup viewGroup1,viewGroup2,viewGroup3,viewGroup4;
    TextView az;
    int i = 0;
    int vg1 = 0,vg2 = 0,vg3 = 0,vg4 = 0;
    EditText ed1,ed2,ed3,ed4;
    String ea1,ea2,ea3,ea4;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        //createGroupList();
        //createCollection();
        RadioGroup rg1,rg2,rg3,rg4;
        Bundle bundle  = this.getArguments();

        View v = inflater.inflate(R.layout.fragment_main,null);

       /* mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CrimeExpandableAdapter crimeExpandableAdapter = new CrimeExpandableAdapter(getActivity(), generateCrimes());
        crimeExpandableAdapter.onRestoreInstanceState(savedInstanceState);

        mCrimeRecyclerView.setAdapter(crimeExpandableAdapter);*/

        /*expListView = (ExpandableListView) v.findViewById(R.id.ep_list);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(),groupList,null);
        expListView.setAdapter(adapter);*/
        az = (TextView) v.findViewById(R.id.txtscore);


        az.setText(i+"/30");

        /// set ViewGroup ///


        viewGroup1 = (CustomViewGroup) v.findViewById(R.id.viewgroup1);
        viewGroup2 = (CustomViewGroup) v.findViewById(R.id.viewgroup2);
        viewGroup3 = (CustomViewGroup) v.findViewById(R.id.viewgroup3);
        viewGroup4 = (CustomViewGroup) v.findViewById(R.id.viewgroup4);
        /// set text ///

        viewGroup1.settxt("1.หัวข้อ");
        viewGroup2.settxt("2.หัวข้อ");
        viewGroup3.settxt("3.หัวข้อ");
        viewGroup4.settxt("4.หัวข้อ");

        /// set radiobutton ///

        rg1 = (RadioGroup) viewGroup1.findViewById(R.id.customradiogroup);
        rg2 = (RadioGroup) viewGroup2.findViewById(R.id.customradiogroup);
        rg3 = (RadioGroup) viewGroup3.findViewById(R.id.customradiogroup);
        rg4 = (RadioGroup) viewGroup4.findViewById(R.id.customradiogroup);


        /// set edittext ///


        ed1 = (EditText) viewGroup1.findViewById(R.id.customedt);
        ed2 = (EditText) viewGroup2.findViewById(R.id.customedt);
        ed3 = (EditText) viewGroup3.findViewById(R.id.customedt);
        ed4 = (EditText) viewGroup4.findViewById(R.id.customedt);


        /// setOncheck ///

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.customrb1 :
                        Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                        viewGroup1.setinvisble();
                        vg1 = viewGroup1.setvaluerb(R.id.customrb1);
                        az.setText("1/30");
                        break;
                    case R.id.customrb2 :
                        Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                        vg1 = viewGroup1.setvaluerb(R.id.customrb2);
                        viewGroup1.setvisble();
                        ea1 = ed1.getText().toString();
                        az.setText("1/30");
                        break;
                }
        }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.customrb1 :
                        Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                        viewGroup2.setinvisble();
                        vg2 = viewGroup2.setvaluerb(R.id.customrb1);
                        az.setText("2/30");
                        break;

                    case R.id.customrb2 :
                        Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                        viewGroup2.setvisble();
                        vg2 = viewGroup2.setvaluerb(R.id.customrb2);
                        ea2 = ed2.getText().toString();
                        az.setText("2/30");
                        break;
                }
            }
        });

        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId)
                {
                    case R.id.customrb1 :
                        Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                        viewGroup3.setinvisble();
                        vg3 = viewGroup3.setvaluerb(R.id.customrb1);
                        i++;
                        az.setText("3/30");
                        break;
                    case R.id.customrb2 :
                        Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                        vg3 = viewGroup3.setvaluerb(R.id.customrb2);
                        viewGroup3.setvisble();
                        ea3 = ed3.getText().toString();
                        az.setText("3/30");
                        break;
                }
            }
        });

        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.customrb1 :
                        Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                        viewGroup4.setinvisble();
                        vg4 = viewGroup4.setvaluerb(R.id.customrb1);
                        az.setText("4/30");
                        break;

                    case R.id.customrb2 :
                        Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                        viewGroup4.setvisble();
                        vg4 = viewGroup4.setvaluerb(R.id.customrb2);
                        ea4 = ed4.getText().toString();
                        az.setText("4/30");
                        break;
                }
            }
        });









        /////////////////////////////////////////////////////////////////////////////////////////////

        TextView ax = (TextView) v.findViewById(R.id.txttest1);
        String a = bundle.getString("Key");
        ax.setText(a);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),vg1
                        +" " + ed1.getText().toString()
                        +" บันทึกสำเร็จ "
                        + vg2 +" "
                        + ed2.getText().toString() ,Toast.LENGTH_SHORT).show();
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
