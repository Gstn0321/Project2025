package com.example.hro_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddWorkerActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, skillsEditText, totalPayEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        dbHelper = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        skillsEditText = findViewById(R.id.skillsEditText);
        totalPayEditText = findViewById(R.id.totalPayEditText);
    }

    public void onSaveWorkerClick(View view) {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String skills = skillsEditText.getText().toString();
        double totalPay = Double.parseDouble(totalPayEditText.getText().toString());

        long id = dbHelper.addWorker(name, phone, skills, totalPay);

        if (id != -1) {
            Toast.makeText(this, "작업자 추가 성공", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "작업자 추가 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
