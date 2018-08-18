package ru.job4j.exam.pojo;

import java.util.Objects;

public class Exam {

    private int id;
    private String name;
    private long time;
    private int result;

    public Exam(int id, String name, long time, int result) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exam exam = (Exam) obj;
        return id == exam.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", result=" + result +
                '}';
    }
}
