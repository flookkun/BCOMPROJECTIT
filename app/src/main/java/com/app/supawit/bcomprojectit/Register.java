package com.app.supawit.bcomprojectit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends AppCompatActivity {

    ConnectionSQL connectionSQL;
    EditText edtuser,edtpass,edtfullname,edtstcode,edttel,edtemail;
    Button btnreg,btnb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doreg doreg = new Doreg();
                doreg.execute("");
            }
        });

        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objI = new Intent(getApplicationContext(),Login.class);
                startActivity(objI);
                finish();
            }
        });
    }


    private void init() {
        connectionSQL = new ConnectionSQL();
        edtuser = (EditText) findViewById(R.id.txtuser);
        edtpass = (EditText) findViewById(R.id.txtpass);
        edtfullname = (EditText) findViewById(R.id.txtfullname);
        edtstcode = (EditText) findViewById(R.id.txtstcode);
        edttel = (EditText) findViewById(R.id.txttel);
        edtemail = (EditText) findViewById(R.id.txtemail);
        btnreg = (Button) findViewById(R.id.btns);
        btnb = (Button) findViewById(R.id.btn2s);

    }
    public class Doreg extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuser.getText().toString();
        String password = edtpass.getText().toString();
        String fullname = edtfullname.getText().toString();
        String email = edtemail.getText().toString();
        String stcode = edtstcode.getText().toString();
        String tel = edttel.getText().toString();
        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(Register.this,r, Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
                finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals("")||fullname.trim().equals("")|| email.trim().equals("")||stcode.trim().equals("")|| tel.trim().equals(""))
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
                                String query = "insert mas_st_pj(STCODE,FULLNAME,TEL,EMAIL)" +
                                        "values('" + stcode + "','" + fullname + "','" + tel + "','" + email + "')" +
                                        "insert sqltest(UserId,Password,STCODE,CFLAG)" +
                                        "values('" + userid + "','" + password + "','" + stcode + "','0')";
                                Statement stmt = con.createStatement();
                                //ResultSet rs = stmt.executeQuery(query);
                                stmt.executeUpdate(query);
                                z = "Successfull";
                                isSuccess = true;
                            }
                        }catch (SQLException ex) {
                            z = "Username ซ้ำกรุณาลองใหม่อีกครั้ง !!";
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
