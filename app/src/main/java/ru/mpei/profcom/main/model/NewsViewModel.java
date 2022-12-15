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
import ru.mpei.profcom.main.model.entities.NewsDto;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class NewsViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<ArrayList<NewsDto>> newsData = new MutableLiveData<>();

    public void observeNews(LifecycleOwner l, Observer<ArrayList<NewsDto>> o){
        newsData.observe(l, o);
    }

    public void getNews(){
        api.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<NewsDto>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull List<NewsDto> newsDtos) {
                    newsData.postValue((ArrayList<NewsDto>) newsDtos);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

}
