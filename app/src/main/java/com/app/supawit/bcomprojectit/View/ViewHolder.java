package com.app.supawit.bcomprojectit.View;

import android.view.View;

import java.util.HashMap;

/**
 * Created by froz on 4/21/2016.
 */
public class ViewHolder {

    private HashMap<Integer, View> storedViews = new HashMap<Integer, View>();

    public ViewHolder()
    {
    }

    /**
     *
     * @param view
     *            The view to add; to reference this view later, simply refer to its id.
     * @return This instance to allow for chaining.
     */
    public ViewHolder addView(View view)
    {
        int id = view.getId();
        storedViews.put(id, view);
        return this;
    }

    public View getView(int id)
    {
        return storedViews.get(id);
    }

}
