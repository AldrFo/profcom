package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.SingleObserver;
import ru.mpei.profcom.main.model.entities.TaskDto;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class TaskViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<ArrayList<TaskDto>> tasksData = new MutableLiveData<>();

    public void observeTasksData(LifecycleOwner l, Observer<ArrayList<TaskDto>> o){
        tasksData.observe(l, o);
    }

    public void getTasksData(){

    }
}
