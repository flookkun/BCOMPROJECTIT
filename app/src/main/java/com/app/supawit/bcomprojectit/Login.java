package com.app.supawit.bcomprojectit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {

    ConnectionSQL connectionSQL;
    EditText etuserid,edtpass;
    Button btnlogin,btnreg;
    ProgressBar pbbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        connectionSQL = new ConnectionSQL();
        etuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin = new Dologin();
                dologin.execute("");
            }
        });

    }

    public class Dologin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = etuserid.getText().toString();
        String password = edtpass.getText().toString();
        String stcode = "";

        @Override
        protected void onPreExecute(){
            pbbar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(Login.this,r, Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Intent i = new Intent(Login.this, Main.class);
                i.putExtra("STCODE",stcode);
                startActivity(i);
                finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionSQL.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "USE[CMD-BX]" +
                                       "select * from mas_st " +
                                       "where STCODE ='" + userid +
                                       "' and STPASSWORD ='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            stcode = rs.getString("stcode");
                            z = "Login successfull";
                            isSuccess = true;
                        }
                        else
                        {
                            z = "รหัสผ่านไม่ถูกต้องกรุณาลองอีกครั้ง";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.toString();
                }
            }
            return z;
        }
    }
}

