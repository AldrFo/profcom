package ru.mpei.profcom.entry.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class EntryViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<Response<ResponseBody>> entryData = new MutableLiveData<>();
    private final MutableLiveData<Response<ResponseBody>> registerData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    public void observeEntry(LifecycleOwner l, Observer<Response<ResponseBody>> o){
        entryData.observe(l, o);
    }

    public void observeRegister(LifecycleOwner l, Observer<Response<ResponseBody>> o){
        registerData.observe(l, o);
    }

    public void entry(String email, String password){
        api.entry(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<Response<ResponseBody>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull Response<ResponseBody> response) {
                    entryData.postValue(response);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    error.postValue(e);
                }
            });
    }

    public void register(String email, String group, String profCard, String password){
        api.register(email, password, group, profCard)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<Response<ResponseBody>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull Response<ResponseBody> response) {
                    registerData.postValue(response);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    error.postValue(e);
                }
            });
    }

}
