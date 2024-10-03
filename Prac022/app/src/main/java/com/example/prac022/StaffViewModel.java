package com.example.prac022;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StaffViewModel extends ViewModel {
    private final MutableLiveData<String> staffInfo = new MutableLiveData<>();
    private final MutableLiveData<String> notification = new MutableLiveData<>();


    public LiveData<String> getStaffInfo() {
        return staffInfo;
    }

    public LiveData<String> getNotification() {
        return notification;
    }

    public void setStaffInfo(String info) {
        staffInfo.setValue(info);
    }

    public void setNotification(String message) {
        notification.setValue(message);
    }
}
