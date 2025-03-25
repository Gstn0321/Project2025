package com.example.hro_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWorkerActivity extends AppCompatActivity {

    private EditText editName, editPhone, editSkills;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        dbHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editSkills = findViewById(R.id.editSkills);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String phone = editPhone.getText().toString();
            String skills = editSkills.getText().toString();

            // 입력 필드 확인 (빈 필드가 있을 경우 경고)
            if (!name.isEmpty() && !phone.isEmpty() && !skills.isEmpty()) {
                dbHelper.addWorker(name, phone, skills);
                Toast.makeText(this, "인력 추가 완료", Toast.LENGTH_SHORT).show();

                // 인력 추가 후 MainActivity로 돌아가기
                Intent intent = new Intent(AddWorkerActivity.this, MainActivity.class);
                startActivity(intent);  // MainActivity로 이동
                finish();  // 현재 Activity 종료
            } else {
                Toast.makeText(AddWorkerActivity.this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
