package com.groupeleven.studentlife.ui.todolist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodolistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TodolistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is To-do List fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}