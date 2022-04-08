package com.example.assignment2fm.ui.grade_entry;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment2fm.DataBaseHelper;
import com.example.assignment2fm.GradeInfo;
import com.example.assignment2fm.R;
import com.example.assignment2fm.databinding.FragmentHomeBinding;
import com.google.android.material.textfield.TextInputLayout;

public class GradeEntryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private GradeEntryViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextInputLayout firstname;
    TextInputLayout lastname;
    TextInputLayout marks;
    Spinner spinner;
    String credits="";
    RadioButton creditsRadioButton;
    String singleCourse="";
    Boolean insertStat;
    DataBaseHelper dbh;
    String[] courses = {"8480","8470", "8460","8450"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(GradeEntryViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        firstname = binding.firstnameTIL;
        lastname = binding.lastnameTIL;
        marks = binding.marksTIL;
        Button btn = binding.submitData;

        // Instantiating Database Helper
        dbh = new DataBaseHelper(getActivity());
        // Taking value from Radio Group
        RadioGroup radioGroup = binding.credits;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.one:
                        credits = "1";
                        break;

                    case R.id.two:
                        credits = "2";
                        break;
                    case R.id.three:
                        credits = "3";
                        break;

                    case R.id.four:
                        credits = "4";
                        break;
                }
            }
        });

        // Subject selection spinner code
        spinner = binding.spinner;
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        // Submit button is clicked
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progID = 0;
                GradeInfo info = new GradeInfo(progID, firstname.getEditText().getText().toString(), lastname.getEditText().getText().toString(),singleCourse,credits,marks.getEditText().getText().toString());
                insertStat = dbh.InsertProgram(info);

                // Check to see if records are inserted or not
                if(insertStat)
                    Toast.makeText(getContext(), "Record Inserted Successfully !", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Record Not Inserted !", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        singleCourse = courses[i];
        Log.i("Course",singleCourse);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}