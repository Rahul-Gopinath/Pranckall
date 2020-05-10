package com.example.pranckall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText edName, edPhone, edEmail, edPassword;
    Button createUser;
    ProgressDialog pd;

    FirebaseAuth mFirebaseAuth;
    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = findViewById(R.id.create_name);
        edPhone = findViewById(R.id.create_phone);
        edEmail = findViewById(R.id.create_email);
        edPassword = findViewById(R.id.create_password);
        createUser = findViewById(R.id.create_user);

        pd = new ProgressDialog(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = edName.getText().toString();
                String txtPhone = edPhone.getText().toString();
                String txtEmail = edEmail.getText().toString().trim();
                String txtPassword = edPassword.getText().toString().trim();

                if(TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(RegisterActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    createUser(txtName, txtPhone, txtEmail, txtPassword);
                }
            }
        });
    }

    private void createUser(final String name, final String phone, String email, String password) {
        pd.setMessage("Please wait");
        pd.show();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String, Object> user = new HashMap<>();
                user.put("name", name);
                user.put("phone", phone);
                user.put("user-id",mFirebaseAuth.getCurrentUser().getUid());
                mRootRef.child("User").child(mFirebaseAuth.getCurrentUser().getUid()).setValue(user);
                Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}
