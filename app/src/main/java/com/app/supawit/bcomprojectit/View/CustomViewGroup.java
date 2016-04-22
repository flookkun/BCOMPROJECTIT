package com.app.supawit.bcomprojectit.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.app.supawit.bcomprojectit.R;

/**
 * Created by froz on 4/20/2016.
 */
public class CustomViewGroup extends FrameLayout {

    CheckBox chkbox;

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
        chkbox = (CheckBox) findViewById(R.id.chkbk);

    }


    public void setChkbox(boolean isCheck){

        chkbox.setChecked(isCheck);

    }

    public void setChkboxtext(String text){
        chkbox.setText(text);
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
