package com.example.hro_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewWorkers;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewWorkers = findViewById(R.id.listViewWorkers);
        dbHelper = new DatabaseHelper(this);

        // 인력 목록을 조회하여 ListView에 표시
        Cursor cursor = dbHelper.getAllWorkers();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"name", "phone"},
                new int[] {android.R.id.text1, android.R.id.text2},
                0
        );
        listViewWorkers.setAdapter(adapter);

        // 인력 추가 버튼 클릭 시 AddWorkerActivity로 이동
        Button btnAddWorker = findViewById(R.id.btnAddWorker);
        btnAddWorker.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWorkerActivity.class);
            startActivity(intent);
        });
    }
}