package com.example.hro_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.SparseBooleanArray;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView workerListView;
    private ArrayList<Worker> workerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        workerListView = findViewById(R.id.workerListView);
        workerList = new ArrayList<>();

        loadWorkerList();
    }

    private void loadWorkerList() {
        Cursor cursor = dbHelper.getAllWorkers();
        if (cursor != null) {
            workerList.clear(); // 리스트 초기화
            ArrayList<String> workerNames = new ArrayList<>();
            int nameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int phoneColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE);
            int skillsColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SKILLS);
            int totalPayColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TOTAL_PAY);

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameColumnIndex);
                int id = cursor.getInt(idColumnIndex);
                String phone = cursor.getString(phoneColumnIndex);
                String skills = cursor.getString(skillsColumnIndex);
                double totalPay = cursor.getDouble(totalPayColumnIndex);

                Worker worker = new Worker(id, name, phone, skills, totalPay);
                workerList.add(worker);
                workerNames.add(name);
            }

            // 어댑터 설정
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, workerNames);
            workerListView.setAdapter(adapter);
            workerListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  // 다중 선택 가능하도록 설정
        }
    }

    public void onAddWorkerClick(View view) {
        Intent intent = new Intent(this, AddWorkerActivity.class);
        startActivity(intent);
    }

    public void onCalculatePayClick(View view) {
        SparseBooleanArray checkedItems = workerListView.getCheckedItemPositions();
        double totalSalary = 0.0;

        for (int i = 0; i < workerList.size(); i++) {
            if (checkedItems.get(i)) { // 선택된 작업자
                totalSalary += workerList.get(i).getTotalPay();
            }
        }

        if (totalSalary > 0) {
            Toast.makeText(this, "선택된 작업자들의 총 급여: " + totalSalary + "원", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "작업자를 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
