package com.app.supawit.bcomprojectit.Fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.supawit.bcomprojectit.ConnectionSQL;
import com.app.supawit.bcomprojectit.R;

import java.io.IOError;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestImageFragment extends Fragment {


    public TestImageFragment() {
        // Required empty public constructor
    }

    ImageView a1;
    ConnectionSQL connectionSQL;
    Statement stmt = null;
    ResultSet rs = null;

    String z = " ";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test_image, null);

        a1 = (ImageView) v.findViewById(R.id.io);

        try {
            connectionSQL = new ConnectionSQL();
            Connection con = connectionSQL.CONN();
            stmt = con.createStatement();
            String truckstring = "select *from ImgTbl2 where id = '6' ";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(truckstring);

            if(rs.next()) {
                z = rs.getString("Img");
                byte[] decodeString = Base64.decode(z, Base64.DEFAULT);
                Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                        0, decodeString.length);
                a1.setImageBitmap(decodebitmap);
            }

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


        return v;
    }

}
