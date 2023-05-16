package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.entry.model.UserData;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class ProfileViewModel extends ViewModel{

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<UserData> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> sendRequestComplete = new MutableLiveData<>();
    private final MutableLiveData<List<String>> availableTime = new MutableLiveData<>();

    public void observeUserLiveData(LifecycleOwner l, Observer<UserData> o){
        userLiveData.observe(l, o);
    }

    public void observeRequestSend(LifecycleOwner l, Observer<Boolean> o){
        sendRequestComplete.observe(l, o);
    }

    public void observeAvailableTime(LifecycleOwner l, Observer<List<String>> o){
        availableTime.observe(l, o);
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

    public void sendRequest(String description){
        api.addActiveRequest(MainActivity.prefs.getInt("id", -1), description)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onComplete() {
                    sendRequestComplete.postValue(true);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

    public void getAvailableTime() {
        api.getAvailablePkTime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<String>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull List<String> strings) {
                    availableTime.postValue(strings);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

    public void sendPkRequest(String time, String description, String vkLink) {
        api.sendPkRequest(MainActivity.prefs.getInt("id", -1), time, description, vkLink)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
