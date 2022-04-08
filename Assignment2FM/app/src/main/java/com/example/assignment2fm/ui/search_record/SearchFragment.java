package com.example.assignment2fm.ui.search_record;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.example.assignment2fm.R;
import com.example.assignment2fm.databinding.FragmentSlideshowBinding;
import com.example.assignment2fm.recyclerAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private SearchViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    Boolean insertStat;
    DataBaseHelper dbh;
    int checkedIdBySearch;
    TextInputLayout til;
    RecyclerView recyclerView;
    TextView tv;
    String singleCourse="";
    String[] courses = {"8480","8470", "8460","8450"};
    private ArrayList<GradeInfo> gradeInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
         new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        til = binding.searchTIL;
        Button btn = binding.searchBtn;
        //Spinner
        Spinner spinner = binding.searchSpinner;
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        til.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        recyclerView = binding.displayRv;
        gradeInfo = new ArrayList<>();
        // Instantiating Database Helper
        dbh = new DataBaseHelper(getActivity());
        RadioGroup radioGroup = binding.searchRG;
        tv = binding.tv;
        tv.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                checkedIdBySearch = checkedId;
                switch(checkedId) {
                    case R.id.searchById:
                        til.setVisibility(View.VISIBLE);
                        spinner.setVisibility(View.INVISIBLE);
                        tv.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.searchByCode:
                        til.setVisibility(View.INVISIBLE);
                        spinner.setVisibility(View.VISIBLE);
                        tv.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(checkedIdBySearch) {
                    case R.id.searchById:
                        searchByUserID();
                        break;

                    case R.id.searchByCode:
                        searchByProgram();
                        setAdapter();
                        break;
                }
            }
        });
        return root;
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(gradeInfo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void searchByProgram(){
        Cursor cursor = dbh.ViewDataByProgramCourse(singleCourse);

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

    public void searchByUserID() {
        Cursor cursor = dbh.ViewDataById(Integer.parseInt(til.getEditText().getText().toString()));
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
                } while (cursor.moveToNext());
            }
        }
        tv.setText(" Id: " + info.getId() + "\n Name: " + info.getFirstname()+info.getLastname() + "\n Course: "+ info.getCourse() +"\n Credits: "+ info.getCredits() + "\n Marks: " + info.getMarks());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        singleCourse = courses[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}