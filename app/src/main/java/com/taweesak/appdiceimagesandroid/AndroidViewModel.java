package com.taweesak.appdiceimagesandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AndroidViewModel extends androidx.lifecycle.AndroidViewModel {

    MutableLiveData<ModelDice> number;

    public AndroidViewModel(@NonNull Application application) {
        super(application);
        number = new MutableLiveData<ModelDice>();
    }

    // รับค่าเข้่ามา
    void setNumber(ModelDice item){
        number.setValue(item);
    }

    //ส่งค่าออกไป
    public LiveData<ModelDice> getData(){
        return number;
    }

}
