package com.app.supawit.bcomprojectit.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.ConnectionSQL;
import com.app.supawit.bcomprojectit.R;
import com.app.supawit.bcomprojectit.View.CustomViewGroup;

import java.io.IOError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{


    ExpandableListView expListView;
    List<String> groupList;
    CustomViewGroup viewGroup1,viewGroup2,viewGroup3,viewGroup4;
    TextView az,date1,date2,date3,date4;
    int i = 0;
    int vg1 = 0,vg2 = 0,vg3 = 0,vg4 = 0;
    int vb1 = 0,vb2 = 0,vb3 = 0,vb4 = 0;
    EditText ed1,ed2,ed3,ed4;
    ConnectionSQL connectionSQL;
    Statement stmt = null;
    ResultSet rs = null;
    String whcode;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        //createGroupList();
        //createCollection();

        final RadioGroup rg1,rg2,rg3,rg4;
        final Bundle bundle  = this.getArguments();
        final String head[] = new String[4];
        final Integer chk = bundle.getInt("chk");
        final View v = inflater.inflate(R.layout.fragment_main,null);

       /* mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        CrimeExpandableAdapter crimeExpandableAdapter = new CrimeExpandableAdapter(getActivity(), generateCrimes());
        crimeExpandableAdapter.onRestoreInstanceState(savedInstanceState);

        mCrimeRecyclerView.setAdapter(crimeExpandableAdapter);*/

        /*expListView = (ExpandableListView) v.findViewById(R.id.ep_list);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(getActivity(),groupList,null);
        expListView.setAdapter(adapter);*/
        az = (TextView) v.findViewById(R.id.txtscore);


        az.setText(i+"/4");
        if(chk==1){

            /// set ViewGroup ///


            viewGroup1 = (CustomViewGroup) v.findViewById(R.id.viewgroup1);
            viewGroup2 = (CustomViewGroup) v.findViewById(R.id.viewgroup2);
            viewGroup3 = (CustomViewGroup) v.findViewById(R.id.viewgroup3);
            viewGroup4 = (CustomViewGroup) v.findViewById(R.id.viewgroup4);
            /// set text ///

            head[0] = viewGroup1.settxt("1.หัวข้อ");
            head[1] = viewGroup2.settxt("2.หัวข้อ");
            head[2] = viewGroup3.settxt("3.หัวข้อ");
            head[3] = viewGroup4.settxt("4.หัวข้อ");

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


            //set textview date///

            date1 = (TextView) viewGroup1.findViewById(R.id.setdate);
            date2 = (TextView) viewGroup2.findViewById(R.id.setdate);
            date3 = (TextView) viewGroup3.findViewById(R.id.setdate);
            date4 = (TextView) viewGroup4.findViewById(R.id.setdate);

            /// set Onclick txt ///



            date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup1.settime();
                }
            });

            date2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup2.settime();
                }
            });

            date3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup3.settime();
                }
            });

            date4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup4.settime();
                }
            });


            /// setOncheck ///

            rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vg1 = viewGroup1.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                            viewGroup1.setinvisble();
                            break;
                        case R.id.customrb2 :
                            vg1 = viewGroup1.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup1.setvisble();
                            //ea1 = ed1.getText().toString();
                            break;
                    }

                    az.setText(i+vg1+vg2+vg3+vg4+"/4");

                }


            });


            rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vg2 = viewGroup2.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                            viewGroup2.setinvisble();
                            // az.setText("2/30");
                            break;

                        case R.id.customrb2 :
                            vg2 = viewGroup2.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup2.setvisble();
                            //ea2 = ed2.getText().toString();
                            //az.setText("2/30");
                            break;
                    }
                    az.setText(i+vg1+vg2+vg3+vg4+"/4");
                }
            });

            rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vg3 = viewGroup3.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                            viewGroup3.setinvisble();
                            break;
                        case R.id.customrb2 :
                            vg3 = viewGroup3.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup3.setvisble();
                            //ea3 = ed3.getText().toString();
                            break;
                    }
                    az.setText(i+vg1+vg2+vg3+vg4+"/4");
                }
            });

            rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vg4 = viewGroup4.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                            viewGroup4.setinvisble();

                            break;

                        case R.id.customrb2 :
                            vg4 = viewGroup4.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup4.setvisble();
                            //ea4 = ed4.getText().toString();
                            break;
                    }
                    az.setText(i+vg1+vg2+vg3+vg4+"/4");
                }
            });











            /////////////////////////////////////////////////////////////////////////////////////////////

            final TextView ax = (TextView) v.findViewById(R.id.txttest1);
            final String a = bundle.getString("Key");
            final String area = bundle.getString("area");
            final String abbname = bundle.getString("wh");



            ax.setText(a);


            /// Link DB //

            try {
                connectionSQL = new ConnectionSQL();
                Connection con = connectionSQL.CONN();
                stmt = con.createStatement();
                String query =
                        "select *from Tmp_whcode where whcode = '"+abbname+"'";

                rs = stmt.executeQuery(query);

                while(rs.next()) {
                    whcode = rs.getString("ABBNAME");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text[] = new String[4];

                    text[0] = ed1.getText().toString();
                    text[1] = ed2.getText().toString();
                    text[2] = ed3.getText().toString();
                    text[3] = ed4.getText().toString();

                    Integer point[] = new Integer[4];
                    point[0] = vg1;
                    point[1] = vg2;
                    point[2] = vg3;
                    point[3] = vg4;

                    String dd[] = new String[4];
                    dd[0] = date1.getText().toString();
                    dd[1] = date2.getText().toString();
                    dd[2] = date3.getText().toString();
                    dd[3] = date4.getText().toString();
                    try {
                        connectionSQL = new ConnectionSQL();
                        Connection con = connectionSQL.CONN();
                        for (int i = 0 ; i < 4 ;i++)
                        {
                            stmt = con.createStatement();
                            String commands = "insert MAS_PJ " +
                                    //"select '"+a+"','"+ head[i]+"','"+area+"',GETDATE(),'"+dd[i]+"','"+text[i]+"','"+ point[i] +"','"+az.getText().toString()+"'";
                                    " VALUES ('"+a+"','"+head[i]+"','"+area+"',CONVERT(VARCHAR(10),GETDATE(),110),'"+dd[i]+"','"+text[i]+"','"+ point[i] +"','"+az.getText().toString()+"')";
                            PreparedStatement preStmt = con.prepareStatement(commands);
                            preStmt.executeUpdate();
                        }
                        stmt = con.createStatement();
                        String commands = "insert MAS_PJ_REPORT " +
                                //"select '"+a+"','"+ head[i]+"','"+area+"',GETDATE(),'"+dd[i]+"','"+text[i]+"','"+ point[i] +"','"+az.getText().toString()+"'";
                                " VALUES ('"+ whcode +"','"+a+"','"+area+"',CONVERT(VARCHAR(10),GETDATE(),110),'"+az.getText().toString()+"')";
                        PreparedStatement preStmt = con.prepareStatement(commands);
                        preStmt.executeUpdate();
                        Toast.makeText(getActivity(),"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show();
                        //เปิดหน้า fragment
                        //getFragmentManager().executePendingTransactions();
                        getFragmentManager().popBackStack();

                    }catch (SQLException ex){
                        Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                    } catch (IOError ex) {
                        Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                    } catch (AndroidRuntimeException ex) {
                        Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException ex) {
                        Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        else if (chk == 0){


            ArrayList<String> list1 = null;
            az = (TextView) v.findViewById(R.id.txtscore);
            final TextView ax = (TextView) v.findViewById(R.id.txttest1);
            final String a = bundle.getString("Key");
            final String area = bundle.getString("area");
            final String abbname = bundle.getString("wh");




            ax.setText(a);
            String olddata;
            final String[] test;
            /// Link DB //

            try {
                connectionSQL = new ConnectionSQL();
                Connection con = connectionSQL.CONN();
                stmt = con.createStatement();
                String query = "select * from mas_pj where DOC_NUMBER = '"+a+"'";

                list1 = new ArrayList<>();

                rs = stmt.executeQuery(query);

                while(rs.next()) {
                    olddata = rs.getString("POINT");
                    list1.add(olddata);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }



            /// set ViewGroup ///


            viewGroup1 = (CustomViewGroup) v.findViewById(R.id.viewgroup1);
            viewGroup2 = (CustomViewGroup) v.findViewById(R.id.viewgroup2);
            viewGroup3 = (CustomViewGroup) v.findViewById(R.id.viewgroup3);
            viewGroup4 = (CustomViewGroup) v.findViewById(R.id.viewgroup4);


            /// set text ///

            head[0] = viewGroup1.settxt("1.หัวข้อ");
            head[1] = viewGroup2.settxt("2.หัวข้อ");
            head[2] = viewGroup3.settxt("3.หัวข้อ");
            head[3] = viewGroup4.settxt("4.หัวข้อ");

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


            //set textview date///

            date1 = (TextView) viewGroup1.findViewById(R.id.setdate);
            date2 = (TextView) viewGroup2.findViewById(R.id.setdate);
            date3 = (TextView) viewGroup3.findViewById(R.id.setdate);
            date4 = (TextView) viewGroup4.findViewById(R.id.setdate);

            /// set Onclick txt ///

            date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup1.settime();
                }
            });

            date2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup2.settime();
                }
            });

            date3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup3.settime();
                }
            });

            date4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewGroup4.settime();
                }
            });



            test = list1.toArray(new String[list1.size()]);


            //set olddata

            vb1 = viewGroup1.setcheckrb(test[0]);
            vb2 = viewGroup2.setcheckrb(test[1]);
            vb3 = viewGroup3.setcheckrb(test[2]);
            vb4 = viewGroup4.setcheckrb(test[3]);
            az.setText(i+vb1+vb2+vb3+vb4+"/4");


            /// setOncheck ///

            rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vb1 = viewGroup1.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                            viewGroup1.setinvisble();
                            break;
                        case R.id.customrb2 :
                            vb1 = viewGroup1.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup1.setvisble();
                            //ea1 = ed1.getText().toString();
                            break;
                    }

                    az.setText(i+vb1+vb2+vb3+vb4+"/4");

                }


            });


            rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vb2 = viewGroup2.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                            viewGroup2.setinvisble();
                            // az.setText("2/30");
                            break;

                        case R.id.customrb2 :
                            vb2 = viewGroup2.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup2.setvisble();
                            //ea2 = ed2.getText().toString();
                            //az.setText("2/30");
                            break;
                    }
                    az.setText(i+vb1+vb2+vb3+vb4+"/4");
                }
            });

            rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vb3 = viewGroup3.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"checkedId",Toast.LENGTH_SHORT).show();
                            viewGroup3.setinvisble();
                            break;
                        case R.id.customrb2 :
                            vb3 = viewGroup3.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup3.setvisble();
                            //ea3 = ed3.getText().toString();
                            break;
                    }
                    az.setText(i+vb1+vb2+vb3+vb4+"/4");
                }
            });

            rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId)
                    {
                        case R.id.customrb1 :
                            vb4 = viewGroup4.setvaluerb(R.id.customrb1);
                            //Toast.makeText(getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                            viewGroup4.setinvisble();

                            break;

                        case R.id.customrb2 :
                            vb4 = viewGroup4.setvaluerb(R.id.customrb2);
                            //Toast.makeText(getContext(),"GG",Toast.LENGTH_SHORT).show();
                            viewGroup4.setvisble();
                            //ea4 = ed4.getText().toString();
                            break;
                    }
                    az.setText(i+vb1+vb2+vb3+vb4+"/4");
                }
            });











            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"555",Toast.LENGTH_SHORT).show();
                }
            });
        }


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
