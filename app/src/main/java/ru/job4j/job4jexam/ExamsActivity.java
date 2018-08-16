package ru.job4j.job4jexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.job4jexam.adapter.ExamAdapter;
import ru.job4j.job4jexam.pojo.Exam;

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
     * Method getExams generate exams list
     * @return
     */
    private List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        for (int index = 0; index <= 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
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
        initRecyclerView();
    }
}
