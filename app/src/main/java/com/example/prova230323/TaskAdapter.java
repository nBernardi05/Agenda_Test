package com.example.prova230323;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasklist = new ArrayList<>();
    private CalendarMainActivity context;
    private String username;

    public TaskAdapter(CalendarMainActivity context, String username) {
        this.context = context;
        this.username = username;
        tasklist = Task.getTasks(context, username, context.endTime.getTime());
    }

    public void deleteTask(int position) {
        Task.deleteTask(context, username, tasklist.get(position));
        context.updateList(username);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getDateView().setText(Task.DATEFORMAT.format(tasklist.get(position).dataora));
        holder.getStartView().setText(Task.TIMEFORMAT.format(tasklist.get(position).dataora));
        holder.getEndView().setText(Task.TIMEFORMAT.format(tasklist.get(position).getEndDate()));
        holder.getTitleView().setText(tasklist.get(position).titolo);
        holder.getButton().setText("X");
        holder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskAdapter.this.deleteTask(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateView;
        private final TextView startView;
        private final TextView endView;
        private final TextView titleView;
        private final Button button;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            dateView = (TextView) view.findViewById(R.id.txtDate);
            startView = (TextView) view.findViewById(R.id.txtStartTime);
            endView = (TextView) view.findViewById(R.id.txtEndTime);
            titleView = (TextView) view.findViewById(R.id.txtTitle);
            button = (Button) view.findViewById(R.id.btnDelete);
        }

        public TextView getDateView() {
            return dateView;
        }

        public TextView getStartView() {
            return startView;
        }

        public TextView getEndView() {
            return endView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public Button getButton() {
            return button;
        }
    }

}
