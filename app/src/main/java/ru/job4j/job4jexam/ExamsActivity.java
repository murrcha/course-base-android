package ru.job4j.job4jexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.job4j.job4jexam.pojo.Exam;

public class ExamsActivity extends AppCompatActivity {

    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

    private RecyclerView mRecycler;
    private ExamAdapter mAdapter;

    private void updateUI() {
        List<Exam> exams = new ArrayList<>();
        for (int index = 0; index <= 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        mAdapter = new ExamAdapter(exams);
        this.mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        mRecycler = findViewById(R.id.exams_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        updateUI();
    }

    private class ExamAdapter extends RecyclerView.Adapter<ExamListHolder> {

        private final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }

        @Override
        public ExamListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_layout_item, parent, false);
            return new ExamListHolder(view);
        }

        @Override
        public void onBindViewHolder(ExamListHolder holder, int position) {
            holder.bind(this.exams.get(position));
        }
    }

    private class ExamListHolder extends RecyclerView.ViewHolder {

        private TextView mIdText;
        private TextView mInfoText;
        private TextView mResultText;
        private TextView mDateText;
        private Exam mExam;

        public ExamListHolder(View itemView) {
            super(itemView);
            mIdText = itemView.findViewById(R.id.id_text);
            mInfoText = itemView.findViewById(R.id.info_text);
            mResultText = itemView.findViewById(R.id.result_text);
            mDateText = itemView.findViewById(R.id.date_text);
        }

        public void bind(Exam exam) {
            this.mExam = exam;
            mIdText.setText(String.valueOf(mExam.getId()));
            mInfoText.setText(mExam.getName());
            mResultText.setText(String.valueOf(mExam.getResult()));
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date(mExam.getTime());
            mDateText.setText(sdf.format(date));
            mInfoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ExamsActivity.this, ExamActivity.class));
                    Toast.makeText(
                            getApplicationContext(), "You select " + mExam,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }
    }
}
