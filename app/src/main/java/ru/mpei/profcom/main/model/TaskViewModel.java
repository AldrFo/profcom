package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.MainActivity;
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
        api.getTasks(MainActivity.prefs.getInt("id", -1))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<TaskDto>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull List<TaskDto> taskDtos) {
                    tasksData.postValue((ArrayList<TaskDto>) taskDtos);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

    public void completeTask(int id){
        api.completeTask(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onComplete() {}

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }
}
