package com.example.assignment2fm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<GradeInfo> gradeList;

    public recyclerAdapter(ArrayList<GradeInfo> gradeInfo){
        this.gradeList = gradeInfo;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nametxt, course, credits, marks;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.nameTv);
            course = itemView.findViewById(R.id.courseTv);
            credits = itemView.findViewById(R.id.creditsTv);
            marks = itemView.findViewById(R.id.marksTv);


        }
    }


    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String firstname = gradeList.get(position).getFirstname();
        String lastname = gradeList.get(position).getLastname();
        String course = gradeList.get(position).getCourse();
        String credits = gradeList.get(position).getCredits();
        String marks = gradeList.get(position).getMarks();

        holder.nametxt.setText("Name: "+ firstname+" "+lastname);
        holder.credits.setText("Course: "+ course);
        holder.marks.setText("Marks: "+marks);
        holder.credits.setText("Credits: "+credits);
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }
}
