package com.app.supawit.bcomprojectit.Expantable;

/**
 * Created by froz on 3/22/2016.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.app.supawit.bcomprojectit.R;
import com.app.supawit.bcomprojectit.View.CustomViewGroup;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    public LayoutInflater inflater;
    private Activity context;
    private Map<String, List<String>> Collections;
    private List<String> Head;
    CustomViewGroup viewgroup1;



    public MyExpandableListAdapter(Activity context, List<String> Head,
                                 Map<String, List<String>> Collections) {
        this.context = context;
        this.Collections = Collections;
        this.Head = Head;
        inflater = context.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return Collections.get(Head.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //ตั้งค่า child
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        View v = inflater.inflate(R.layout.listrow_details,null);

        viewgroup1 = (CustomViewGroup) v.findViewById(R.id.viewgroup1);





        return v;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }


    @Override
    public Object getGroup(int groupPosition) {

        return Head.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return Head.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition)
    {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }





}
