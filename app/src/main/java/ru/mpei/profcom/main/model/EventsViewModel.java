package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.main.model.entities.EventDto;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class EventsViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<ArrayList<EventDto>> eventsData = new MutableLiveData<>();


    public void observeEventsData(LifecycleOwner l, Observer<ArrayList<EventDto>> o){
        eventsData.observe(l, o);
    }


    public void getEvents(int id){
        api.getEvents(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<EventDto>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<EventDto> eventDtos) {
                        eventsData.postValue((ArrayList<EventDto>) eventDtos);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}
                });
    }

    public void addEvent(int id, String name, String description, String link){
        api.addEvent(id, name, description, link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


}
