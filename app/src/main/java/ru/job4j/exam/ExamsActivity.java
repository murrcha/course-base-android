package ru.job4j.exam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.exam.adapter.ExamAdapter;
import ru.job4j.exam.pojo.Exam;
import ru.job4j.exam.store.ExamBaseHelper;
import ru.job4j.exam.store.ExamDBSchema;

public class ExamsActivity extends AppCompatActivity {

    /**
     * recycler
     */
    private RecyclerView recycler;

    /**
     * adapter
     */
    private ExamAdapter adapter;

    /**
     * store db
     */
    private SQLiteDatabase store;

    /**
     * Method getExams generate exams list
     * @return
     */
    private List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        Cursor cursor = this.store.query(
                ExamDBSchema.ExamTable.NAME, null,null,
                null,null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(new Exam(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    System.currentTimeMillis(),
                    100
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return exams;
    }

    /**
     * Method initRecycler init recycler
     */
    private void initRecyclerView() {
        recycler = findViewById(R.id.exams_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExamAdapter();
        adapter.setItems(getExams());
        recycler.setAdapter(adapter);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        this.store = new ExamBaseHelper(getApplicationContext()).getWritableDatabase();
        initRecyclerView();
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exams, menu);
        return true;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_exam:
                startActivity(new Intent(ExamsActivity.this, ExamUpdateActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
