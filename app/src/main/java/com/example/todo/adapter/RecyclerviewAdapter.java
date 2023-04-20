package com.example.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.TodoItem;

import java.util.ArrayList;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private ArrayList<TodoItem> todoItems;

    public RecyclerviewAdapter(ArrayList<TodoItem> todoItems){
        this.todoItems = todoItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, dueDate;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title_textview);
            description = (TextView) v.findViewById(R.id.description_textview);
            dueDate = (TextView) v.findViewById(R.id.duedate_textview);
        }
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoItem item = todoItems.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.dueDate.setText(item.getDueDate());
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }
}
