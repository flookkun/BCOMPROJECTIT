package com.app.supawit.bcomprojectit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit extends AppCompatActivity {
    ConnectionSQL connectionSQL;
    EditText edtpass, edtfullname, edtstcode, edttel, edtemail;
    Button btnstm, btnbtm;
    String stcM = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();

        btnbtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(getApplicationContext(), Main.class);
                Main.putExtra("STCODE",stcM);
                startActivity(Main);
                finish();
            }
        });

        btnstm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doedit doedit = new Doedit();
                doedit.execute();
            }
        });

    }//Oncreate
    private void init() {
        stcM = getIntent().getExtras().getString("STCODE");
        connectionSQL = new ConnectionSQL();
        edtpass = (EditText) findViewById(R.id.edtxtpass);
        edtfullname = (EditText) findViewById(R.id.edtxtfullname);
        edtstcode = (EditText) findViewById(R.id.edtxtstcode);
        edttel = (EditText) findViewById(R.id.edtxttel);
        edtemail = (EditText) findViewById(R.id.edtxtemail);
        btnstm = (Button) findViewById(R.id.btnstm);
        btnbtm = (Button) findViewById(R.id.btnbtm);
    }
    public class Doedit extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String password = edtpass.getText().toString();
        String fullname = edtfullname.getText().toString();
        String email = edtemail.getText().toString();
        String stcode = edtstcode.getText().toString();
        String tel = edttel.getText().toString();
        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(Edit.this,r, Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                i.putExtra("STCODE",stcode);
                startActivity(i);
                finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            if(password.trim().equals("")||fullname.trim().equals("")|| email.trim().equals("")||stcode.trim().equals("")|| tel.trim().equals(""))
                z = "กรุณาใส่ข้อมูลให้ครบทุกช่อง !!";
            else
            {
                try {
                    Connection con = connectionSQL.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {

                        try{
                            if(password.length() < 4){
                                z = "กรุณาใส่รหัสผ่าน 4 ตัวขึ้นไป !!";
                            }
                            else {
                                String query = "update mas_st " +
                                "set STCODE = '"+stcode+"',FULLNAME = '"+fullname+"',TEL = '"+tel+"',EMAIL = '"+email+"' "+
                                "where STCODE = '"+stcM+"' "+
                                "update sqltest "+
                                "set password = '"+password+"',STCODE = '"+stcode+"' "+
                                "where stcode = '"+stcM+"' ";
                                Statement stmt = con.createStatement();
                                //ResultSet rs = stmt.executeQuery(query);
                                stmt.executeUpdate(query);
                                z = "Successfull";
                                isSuccess = true;
                            }
                        }catch (SQLException ex) {
                            z = ex.getMessage();
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }
}