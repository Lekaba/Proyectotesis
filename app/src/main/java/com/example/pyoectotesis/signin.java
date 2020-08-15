package com.example.pyoectotesis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pyoectotesis.Common.Common;
import com.example.pyoectotesis.Mode.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signin extends AppCompatActivity {
EditText edtPhone,edtPassword;
Button btnsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        edtPassword=(MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone=(MaterialEditText)findViewById(R.id.edtPhone);
        btnsignin=(Button)findViewById(R.id.btnSignIn);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(signin.this);
                mDialog.setMessage("Esperé por favor.");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {


                        // Se obtiene la informacion del usuario
                        mDialog.dismiss();
                        User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                        user.setPhone(edtPhone.getText().toString()); ///set Phone

                        if (user.getPassword().equals(edtPassword.getText().toString())) {{
                            Intent homeIntent = new Intent(signin.this,Home.class);
                            Common.currentuser = user;
                            startActivity(homeIntent);
                            finish();
                            table_user.removeEventListener(this);

                        }
                        }
                        else {
                            Toast.makeText(signin.this, "Contraseña incorrecta, favor de verificar.", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(signin.this, "El usuario no existe, favor de verificar.", Toast.LENGTH_SHORT).show();
                        }
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




    }
}
