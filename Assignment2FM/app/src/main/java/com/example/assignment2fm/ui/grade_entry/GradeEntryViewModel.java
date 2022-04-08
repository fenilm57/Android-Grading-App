package com.example.assignment2fm.ui.grade_entry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GradeEntryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GradeEntryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Grade Entry fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}