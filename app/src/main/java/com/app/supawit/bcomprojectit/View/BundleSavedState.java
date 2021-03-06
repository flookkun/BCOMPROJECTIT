package com.app.supawit.bcomprojectit.View;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by froz on 4/21/2016.
 */
public class BundleSavedState extends View.BaseSavedState {

    private Bundle bundle;


    public Bundle getBundle(){
        return bundle;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    public BundleSavedState(Parcel source) {
        super(source);
        bundle = source.readBundle();
    }

    public BundleSavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(bundle);
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new BundleSavedState(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new BundleSavedState[size];
        }
    };

}
