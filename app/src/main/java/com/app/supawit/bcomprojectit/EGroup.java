package com.app.supawit.bcomprojectit;

/**
 * Created by froz on 3/22/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class EGroup {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public EGroup(String string) {

        this.string = string;
    }



}