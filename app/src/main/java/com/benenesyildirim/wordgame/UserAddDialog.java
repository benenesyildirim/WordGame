package com.benenesyildirim.wordgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class UserAddDialog extends Dialog implements View.OnClickListener {

    private EditText userNameET, emailET;
    private Button addBTN, cancelBTN;
    private FirebaseDatabase firebaseDatabase;
    private SharedPreferences savedData;
    private String usernameEtData, emailEtData;
    private boolean isUserCreated = false;

    public UserAddDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_dialog);
        savedData = getContext().getSharedPreferences("savedUserID", MODE_PRIVATE);
        initViewsAndFirebase();
    }

    private void initViewsAndFirebase() {
        userNameET = findViewById(R.id.usernameET);
        emailET = findViewById(R.id.emailET);
        addBTN = findViewById(R.id.addBTN);
        cancelBTN = findViewById(R.id.cancelBTN);
        firebaseDatabase = FirebaseDatabase.getInstance();

        addBTN.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == addBTN.getId()) {
            usernameEtData = userNameET.getText().toString();
            emailEtData = emailET.getText().toString();
            if (!usernameEtData.isEmpty() && !emailEtData.isEmpty()) {
                createAndSaveUser();
            }
            if (usernameEtData.isEmpty() && emailEtData.isEmpty()) {
                Toast.makeText(getContext(), R.string.create_user_empty_message, Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId() == cancelBTN.getId()) {
            dismiss();
        }
    }

    private void createAndSaveUser() {
        String userID = firebaseDatabase.getReference("users").push().getKey();

        SharedPreferences.Editor editor = getContext().getSharedPreferences("savedUserID", MODE_PRIVATE).edit();
        editor.putString("userID", userID);
        editor.apply();

        UserProperties user = new UserProperties(usernameEtData, emailEtData, "lastScore", "highScore", "highScore");
        firebaseDatabase.getReference("users").child(userID).setValue(user);

        dismiss();
        isUserCreated = true;
    }
}
