package com.example.assignment2fm.ui.view_grade;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2fm.DataBaseHelper;
import com.example.assignment2fm.GradeInfo;
import com.example.assignment2fm.databinding.FragmentGalleryBinding;
import com.example.assignment2fm.recyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewGradeFragment extends Fragment {

    private GradeViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    DataBaseHelper dbh;
    private ArrayList<GradeInfo> gradeInfo;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GradeViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbh = new DataBaseHelper(getActivity());
        recyclerView = binding.recyclerView;
        gradeInfo = new ArrayList<>();
        getData();
        setAdapter();
        return root;
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(gradeInfo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getData() {
        Cursor cursor = dbh.ViewData();
        GradeInfo info = new GradeInfo();

        // Taking value from cursor to Program variable
        if(cursor == null) {
            Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
        } else {
            if(cursor.moveToFirst()) {
                do{
                    info.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    info.setFirstname(cursor.getString(cursor.getColumnIndex("firstname")));
                    info.setLastname(cursor.getString(cursor.getColumnIndex("lastname")));
                    info.setCourse(cursor.getString(cursor.getColumnIndex("course")));
                    info.setCredits(cursor.getString(cursor.getColumnIndex("credits")));
                    info.setMarks(cursor.getString(cursor.getColumnIndex("marks")));
                    gradeInfo.add(new GradeInfo(info.getId(),info.getFirstname() ,info.getLastname(),info.getCourse(),info.getCredits(),info.getMarks()));
                } while (cursor.moveToNext());
            }
        }
    }
}