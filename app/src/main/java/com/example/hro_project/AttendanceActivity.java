package com.example.hro_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AttendanceActivity extends AppCompatActivity {

    private EditText workerIdEditText, checkInEditText, checkOutEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        dbHelper = new DatabaseHelper(this);

        workerIdEditText = findViewById(R.id.workerIdEditText);
        checkInEditText = findViewById(R.id.checkInEditText);
        checkOutEditText = findViewById(R.id.checkOutEditText);
    }

    public void onSaveAttendanceClick(View view) {
        int workerId = Integer.parseInt(workerIdEditText.getText().toString());
        String checkIn = checkInEditText.getText().toString();
        String checkOut = checkOutEditText.getText().toString();

        long id = dbHelper.addAttendance(workerId, checkIn, checkOut);

        if (id != -1) {
            Toast.makeText(this, "출퇴근 기록 저장 성공", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "출퇴근 기록 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
