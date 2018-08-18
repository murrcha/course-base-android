package ru.job4j.exam.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ExamBaseHelper
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class ExamBaseHelper extends SQLiteOpenHelper {
    public static final String DB = "exams.db";
    public static final int VERSION = 1;

    public ExamBaseHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format(
                "create table %s (id integer primary key autoincrement, %s )"
                , ExamDBSchema.ExamTable.NAME
                , ExamDBSchema.ExamTable.Cols.TITLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
