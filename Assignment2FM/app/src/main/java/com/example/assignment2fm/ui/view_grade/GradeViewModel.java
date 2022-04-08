package com.example.assignment2fm.ui.view_grade;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GradeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GradeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Grade View fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}