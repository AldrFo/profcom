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
import ru.mpei.profcom.main.model.entities.InfoDto;
import ru.mpei.profcom.main.model.entities.OrgDto;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class OrgViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<ArrayList<OrgDto>> orgsData = new MutableLiveData<>();

    public void observeOrgs(LifecycleOwner l, Observer<ArrayList<OrgDto>> o){
        orgsData.observe(l, o);
    }

    public void getOrgs(){
        api.getOrgs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<OrgDto>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull List<OrgDto> orgDtos) {
                    orgsData.postValue((ArrayList<OrgDto>) orgDtos);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

}
