package ru.job4j.exam.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ru.job4j.exam.ExamActivity;
import ru.job4j.exam.R;
import ru.job4j.exam.pojo.Exam;

/**
 * ExamAdapter
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    /**
     * List exams
     */
    private final List<Exam> exams = new ArrayList<>();

    /**
     * ${@inheritDoc}
     */
    @Override
    public ExamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_layout_item, parent, false);
        return new ExamViewHolder(view);
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public void onBindViewHolder(ExamViewHolder holder, int position) {
        holder.bind(exams.get(position));
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return exams.size();
    }

    /**
     * Method setItems add new items to exams list
     * @param exams
     */
    public void setItems(Collection<Exam> exams) {
        this.exams.addAll(exams);
        notifyDataSetChanged();
    }

    /**
     * Method clearItems clear exams list
     */
    public void clearItems() {
        this.exams.clear();
        notifyDataSetChanged();
    }

    /**
     * ExamViewHolder
     */
    class ExamViewHolder extends RecyclerView.ViewHolder {

        /**
         * Date format
         */
        private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

        /**
         * UI elements
         */
        private TextView idText;
        private TextView infoText;
        private TextView resultText;
        private TextView dateText;
        private Exam exam;

        /**
         * Init view holder
         * @param itemView view
         */
        public ExamViewHolder(View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id_text);
            infoText = itemView.findViewById(R.id.info_text);
            resultText = itemView.findViewById(R.id.result_text);
            dateText = itemView.findViewById(R.id.date_text);
        }

        /**
         * Method bind binding view and data from exam
         * @param exam
         */
        public void bind(Exam exam) {
            this.exam = exam;
            idText.setText(String.valueOf(this.exam.getId()));
            infoText.setText(this.exam.getName());
            resultText.setText(String.valueOf(this.exam.getResult()));
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date(this.exam.getTime());
            dateText.setText(sdf.format(date));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), ExamActivity.class));
                    Toast.makeText(
                            itemView.getContext(), "You select " + ExamViewHolder.this.exam,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }
    }
}
