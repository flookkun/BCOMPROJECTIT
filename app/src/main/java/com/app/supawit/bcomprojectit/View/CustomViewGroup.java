package com.app.supawit.bcomprojectit.View;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.supawit.bcomprojectit.R;

import java.util.Calendar;

/**
 * Created by froz on 4/20/2016.
 */
public class CustomViewGroup extends FrameLayout {

    private RadioGroup rbg1;
    private RadioButton rb1,rb2;
    private TextView txt,setdate,date;
    private EditText edt;
    int value = 0;
    private int mYear, mMonth, mDay;
    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    @TargetApi(21)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }


    private void initInflate(){
        //inflate
        inflate(getContext(), R.layout.customview,this);
    }


    private void initInstances(){
        //findview by id here
        txt = (TextView) findViewById(R.id.customtxt);
        edt = (EditText) findViewById(R.id.customedt);
        rbg1 = (RadioGroup) findViewById(R.id.customradiogroup);
        rb1 = (RadioButton) findViewById(R.id.customrb1);
        rb2 = (RadioButton) findViewById(R.id.customrb2);
        setdate = (TextView) findViewById(R.id.setdate);
        date = (TextView) findViewById(R.id.date);

    }

    public String settxt (String a){
        txt.setText(a);
        return a;
    }


    public void setvisble (){

        edt.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        setdate.setVisibility(View.VISIBLE);
    }

    public void setinvisble(){
        edt.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
        setdate.setVisibility(View.INVISIBLE);
    }

    public int setvaluerb(int h){

        if(h == R.id.customrb1 )
        {
            value = 1;
        }
        else if (h == R.id.customrb2)
        {
            value = 0;
        }

        return value;
    }

    public int setcheckrb(String a){


        if (a == "1"){
            rb1.setChecked(true);
            value = 1;
            //setvaluerb(value);
        }
        else{
            rb2.setChecked(true);
            value = 0;
            setvisble();
            //setvaluerb(value);
        }

        return value;

    }

    public void settime (){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getContext() ,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dd = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                setdate.setText(dd);
            }
        },mYear,mMonth,mDay );
        dpd.show();




    }


    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle childrenStates = new Bundle();
        for (int i = 0 ; i < getChildCount(); i++)
        {
            int id = getChildAt(i).getId();
            if (id != 0) {

                SparseArray childrenState = new SparseArray();
                getChildAt(i).saveHierarchyState(childrenState);
                childrenStates.putSparseParcelableArray(String.valueOf(id),childrenState);

            }
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("childrenState",childrenStates);


        BundleSavedState ss = new BundleSavedState(superState);
        ss.setBundle(bundle);

        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle childrenStates = ss.getBundle().getBundle("childrenState");

        for (int i = 0;i<getChildCount();i++)
        {
            int id = getChildAt(i).getId();

            if (id != 0) {
                if (childrenStates.containsKey(String.valueOf(id))){
                    SparseArray childrenState =
                            childrenStates.getSparseParcelableArray(String.valueOf(id));
                    getChildAt(i).restoreHierarchyState(childrenState);
                }


            }
        }

    }
}
