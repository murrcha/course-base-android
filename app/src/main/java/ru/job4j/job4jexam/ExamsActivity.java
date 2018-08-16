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

    private RecyclerView recycler;
    private ExamAdapter adapter;

    private void updateUI() {
        List<Exam> exams = new ArrayList<>();
        for (int index = 0; index <= 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        adapter = new ExamAdapter(exams);
        this.recycler.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        recycler = findViewById(R.id.exams_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
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

        private TextView idText;
        private TextView infoText;
        private TextView resultText;
        private TextView dateText;
        private Exam exam;

        public ExamListHolder(View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id_text);
            infoText = itemView.findViewById(R.id.info_text);
            resultText = itemView.findViewById(R.id.result_text);
            dateText = itemView.findViewById(R.id.date_text);
        }

        public void bind(Exam exam) {
            this.exam = exam;
            idText.setText(String.valueOf(this.exam.getId()));
            infoText.setText(this.exam.getName());
            resultText.setText(String.valueOf(this.exam.getResult()));
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date(this.exam.getTime());
            dateText.setText(sdf.format(date));
            infoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ExamsActivity.this, ExamActivity.class));
                    Toast.makeText(
                            getApplicationContext(), "You select " + ExamListHolder.this.exam,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }
    }
}
