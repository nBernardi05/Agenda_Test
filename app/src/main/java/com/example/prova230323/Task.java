package com.example.prova230323;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Task implements Comparable<Task> {

    public static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ITALIAN);
    public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.ITALIAN);
    public static final SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("HH:mm", Locale.ITALIAN);

    public final Date dataora;
    public final Duration durata;
    public final String titolo;

    public Task(Date dataora, Duration durata, String titolo) {
        this.dataora = dataora;
        this.durata = durata;
        this.titolo = titolo;
    }


    // to text representation
    @NonNull
    @Override
    public String toString() {
        return DATETIMEFORMAT.format(dataora) + "@" + durata.getSeconds() + "@" + titolo;
    }

    // constructor from text representation
    public Task(String s) {
        Date deferred;
        String[] split = s.split("@", 3);
        try {
            deferred = DATETIMEFORMAT.parse(split[0]);
        } catch (ParseException e) {
            deferred = new Date();
        }
        dataora = deferred;
        durata = Duration.ofSeconds(Long.parseLong(split[1]));
        titolo = split[2];
    }

    // alternative: static factory method
    public static Task parse(String s) {
        Date dataora;
        String[] split = s.split("@", 3);
        try {
            dataora = DATETIMEFORMAT.parse(split[0]);
        } catch (ParseException e) {
            dataora = new Date();
        }
        return new Task(dataora, Duration.ofSeconds(Long.parseLong(split[1])), split[2]);
    }

    public Date getEndDate() {
        Date endDate = new Date();
        endDate.setTime(dataora.getTime() + durata.toMillis());
        return endDate;
    }

    public static void addTask(Context context, String username, Task task) {
        SharedPreferences usertasks = context.getSharedPreferences(username, Context.MODE_PRIVATE);
        String key = Task.DATEFORMAT.format(task.dataora);
        Set<String> dayTasks = usertasks.getStringSet(key, new TreeSet<>());
        dayTasks.add(task.toString());
        SharedPreferences.Editor edit = usertasks.edit();
        edit.putStringSet(key, dayTasks);
        Toast.makeText(context, "Task added successfully", Toast.LENGTH_LONG).show();
        edit.commit(); // edit.apply();
    }

    public static void deleteTask(Context context, String username, Task task) {
        SharedPreferences usertasks = context.getSharedPreferences(username, Context.MODE_PRIVATE);
        String key = Task.DATEFORMAT.format(task.dataora);
        Set<String> dayTasks = usertasks.getStringSet(key, new TreeSet<>());
        dayTasks.remove(task.toString());
        SharedPreferences.Editor edit = usertasks.edit();
        edit.putStringSet(key, dayTasks);
        Toast.makeText(context, "Task deleted successfully", Toast.LENGTH_LONG).show();
        edit.commit(); // edit.apply();
    }

    public static List<Task> getTasks(Context context, String username, Date date) {
        SharedPreferences usertasks = context.getSharedPreferences(username, Context.MODE_PRIVATE);
        String key = Task.DATEFORMAT.format(date);
        Set<String> dayTasks = usertasks.getStringSet(key, new TreeSet<>());
        List<Task> result = new ArrayList<>(dayTasks.size());
        for (String t : dayTasks) {
//            result.add(new Task(t));
            result.add(Task.parse(t));
        }
        Collections.sort(result);
        return result;
    }


    @Override
    public int compareTo(Task task) {
        return dataora.compareTo(task.dataora);
    }
}
