package com.app.supawit.bcomprojectit;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by froz on 3/7/2016.
 */
public class ConnectionSQL {

    String ip = "5cosmeda.homeunix.com:1733";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "flooketest";
    String usersql = "sa";
    String passwordsql = "0211";

    @SuppressLint("NewApi")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String str = null;
        try{
            Class.forName(classs);
            str = "jdbc:jtds:sqlserver://"+ ip +";"+"databaseName="+db+";user="+usersql+";password="+passwordsql+";";
            conn = DriverManager.getConnection(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return conn;
    }



}
