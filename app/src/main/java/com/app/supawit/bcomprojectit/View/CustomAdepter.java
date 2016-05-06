package com.app.supawit.bcomprojectit.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.supawit.bcomprojectit.NewsItem;
import com.app.supawit.bcomprojectit.R;

import java.util.ArrayList;

/**
 * Created by froz on 5/6/2016.
 */
public class CustomAdepter extends BaseAdapter {

    Context mContext;
    String[] str;
    int[] resId;
    private ArrayList<NewsItem> listdata;
    private LayoutInflater layoutInflater;

    public CustomAdepter (Context mContext,ArrayList<NewsItem> listdata){

        this.listdata = listdata;
        layoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.list_history,null);
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.doc_number);
            holder.whName = (TextView) convertView.findViewById(R.id.txtwh);
            holder.point = (TextView) convertView.findViewById(R.id.txts);
            holder.docdate = (TextView) convertView.findViewById(R.id.txtdate);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.headlineView.setText(listdata.get(position).getHeadline());
        holder.whName.setText(listdata.get(position).getWhName());
        holder.point.setText(listdata.get(position).getScore());
        holder.docdate.setText(listdata.get(position).getDocdate());

        return convertView;
    }

    public class ViewHolder {
        TextView headlineView;
        TextView whName;
        TextView point;
        TextView docdate;
    }
}
