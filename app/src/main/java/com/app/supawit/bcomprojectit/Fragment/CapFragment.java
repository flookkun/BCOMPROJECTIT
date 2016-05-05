package com.app.supawit.bcomprojectit.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.ConnectionSQL;
import com.app.supawit.bcomprojectit.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * A simple {@link Fragment} subclass.
 */
public class CapFragment extends Fragment {


    public CapFragment() {
        // Required empty public constructor
    }
    ImageView image,image2,image3,image4,image5,image6;
    byte[] byteArray;
    Button btncap;
    CardView c1;
    String encodedImage;
    ConnectionSQL connectionSQL;
    Statement stmt = null;
    ResultSet rs = null;

    public static final int REQUEST_CAMERA = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle  = this.getArguments();
        View v = inflater.inflate(R.layout.fragment_cap, null);
        final TextView show = (TextView) v.findViewById(R.id.tt);
        final String ss = bundle.getString("Key");
        show.setText(ss);
        image = (ImageView) v.findViewById(R.id.ima1);
        image2 = (ImageView) v.findViewById(R.id.ima2);
        image3 = (ImageView) v.findViewById(R.id.ima3);
        image4 = (ImageView) v.findViewById(R.id.ima4);
        image5 = (ImageView) v.findViewById(R.id.ima5);
        image6 = (ImageView) v.findViewById(R.id.ima6);
        btncap = (Button) v.findViewById(R.id.btnCap);
        c1 = (CardView) v.findViewById(R.id.card1qa);

        //// capture onclick ////

        btncap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(it,REQUEST_CAMERA);
                        btncap.setVisibility(View.INVISIBLE);
                        c1.setVisibility(View.INVISIBLE);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,4);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,5);
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,6);
            }
        });

        ///// floating button onclick /////
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    connectionSQL = new ConnectionSQL();
                    Connection con = connectionSQL.CONN();
                    stmt = con.createStatement();
                    String commands = "Insert into ImgTbl2 (ImgName,Img) values ('"
                            + ss + "','" + encodedImage
                            + "')";
                    PreparedStatement preStmt = con.prepareStatement(commands);
                    preStmt.executeUpdate();

                    Toast.makeText(getActivity(),"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show();
                    //เปิดหน้า fragment
                    //getFragmentManager().executePendingTransactions();
                    getFragmentManager().popBackStack();
                }catch (SQLException ex){
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                } catch (IOError ex) {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                } catch (AndroidRuntimeException ex) {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                } catch (NullPointerException ex) {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_SHORT).show();
                }

               /* Toast.makeText(getActivity(),"บันทึกสำเร็จ",Toast.LENGTH_SHORT).show();
                //เปิดหน้า fragment
                //getFragmentManager().executePendingTransactions();
                getFragmentManager().popBackStack();*/
            }
        });
        return v;
    }


    //// OnActicityEvent ///

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA){
                image2.setVisibility(View.VISIBLE);
                Bitmap pic = (Bitmap) data.getExtras().get("data");
                //Uri d = data.getData();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);

                byteArray = bytes.toByteArray();
                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image.setImageBitmap(pic);
                //Toast.makeText(getContext(),encodedImage,Toast.LENGTH_LONG).show();

            }
            else if (requestCode == 2){
                image3.setVisibility(View.VISIBLE);
                Bitmap pic = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image2.setImageBitmap(pic);
            }
            else if (requestCode == 3){
                image4.setVisibility(View.VISIBLE);
                Bitmap pic = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image3.setImageBitmap(pic);
            }
            else if (requestCode == 4){
                image5.setVisibility(View.VISIBLE);
                Bitmap pic = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image4.setImageBitmap(pic);
            }
            else if (requestCode == 5){
                image6.setVisibility(View.VISIBLE);
                Bitmap pic = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image5.setImageBitmap(pic);
            }
            else if (requestCode == 6){

                Bitmap pic = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                image6.setImageBitmap(pic);
            }
        }
    }
}





