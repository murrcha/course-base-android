package ru.job4j.exam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.job4j.exam.store.ExamBaseHelper;
import ru.job4j.exam.store.ExamDBSchema;

public class ExamUpdateActivity extends AppCompatActivity {

    private SQLiteDatabase store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_update);
        this.store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        final EditText edit = findViewById(R.id.title_edit_text);
        Button save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(ExamDBSchema.ExamTable.Cols.TITLE, edit.getText().toString());
                store.insert(ExamDBSchema.ExamTable.NAME, null, values);
                startActivity(new Intent(ExamUpdateActivity.this, ExamsActivity.class));
            }
        });
    }
}
