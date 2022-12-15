package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.entry.model.UserData;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class ProfileViewModel extends ViewModel{

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<UserData> userLiveData = new MutableLiveData<>();

    public void observeUserLiveData(LifecycleOwner l, Observer<UserData> o){
        userLiveData.observe(l, o);
    }

    public void getUserData(int id){
        api.getUserData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull UserData userData) {
                        userLiveData.postValue(userData);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}
                });
    }
}
