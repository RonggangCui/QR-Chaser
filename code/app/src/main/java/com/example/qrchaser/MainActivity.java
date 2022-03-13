package com.example.qrchaser;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button email,qrCode,guest,createAccount;
    private String qrValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_hub);

        email = findViewById(R.id.buttonUsernameLogin);
        createAccount = findViewById(R.id.buttonCreateAccount);
        qrCode = findViewById(R.id.buttonQRCodeLogin);
        guest = findViewById(R.id.buttonGuestLogin);


        ActivityResultLauncher<Intent> scannerResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Intent scannerResult = result.getData();
                            qrValue = scannerResult.getStringExtra("qrValue");

                            //for testing the result
                            Toast.makeText(getApplicationContext(), qrValue, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginEmailActivity.class);
                startActivity(intent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraScannerActivity.class);
                scannerResultLauncher.launch(intent);
            }
        });


        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyQRCodeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}