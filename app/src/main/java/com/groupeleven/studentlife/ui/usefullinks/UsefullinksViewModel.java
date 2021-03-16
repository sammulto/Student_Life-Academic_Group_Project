package com.groupeleven.studentlife.ui.usefullinks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsefullinksViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public UsefullinksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Useful Links fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
