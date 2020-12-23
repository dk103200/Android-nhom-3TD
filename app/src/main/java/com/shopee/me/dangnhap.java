package com.shopee.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shopee.MainActivity;
import com.shopee.R;

public class dangnhap extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    TextView dangky,btnSignIn;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        edtPhone = (EditText) findViewById(R.id.edt_user);
        edtPassword = (EditText) findViewById(R.id.edt_pass);
        btnSignIn =  findViewById(R.id.btn_login);
        dangky = (TextView) findViewById(R.id.tv_signup);
        imgBack = (ImageView) findViewById(R.id.back);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rg = new Intent(dangnhap.this, dangky_activity.class);
                startActivity(rg);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog mDialog = new ProgressDialog(dangnhap.this);
                mDialog.setMessage("Please Waiting!");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Check
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {

                            //Get User information
                            mDialog.dismiss();
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            //PArt 5:
                            user.setPhone(edtPhone.getText().toString());//set Phone

                            if (user.getPassword().equals(edtPassword.getText().toString())) {

                                Toast.makeText(dangnhap.this, "Đăng Nhập Thành Công !", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(dangnhap.this, MainActivity.class);
                                homeIntent.putExtra("user_name", user.getName());
                                homeIntent.putExtra("user_id", user.getPhone());

                                startActivity(homeIntent);
                               finish();

                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(dangnhap.this, "Sai tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(dangnhap.this, "Sai tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}