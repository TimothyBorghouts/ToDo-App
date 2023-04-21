package com.example.todo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.TodoItem;
import com.example.todo.ui.UpdateActivity;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private ArrayList<TodoItem> todoItems;
    private Context context;

    public RecyclerviewAdapter(ArrayList<TodoItem> todoItems, Context context){
        this.todoItems = todoItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, dueDate;
        LinearLayout recyclerview_layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            title = v.findViewById(R.id.title_textview);
            description = v.findViewById(R.id.description_textview);
            dueDate = v.findViewById(R.id.duedate_textview);
            recyclerview_layout = itemView.findViewById(R.id.recyclerview_item);
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

        holder.recyclerview_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", item.getId());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("dueDate", item.getDueDate());
                intent.putExtra("notes", item.getNotes());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }
}
