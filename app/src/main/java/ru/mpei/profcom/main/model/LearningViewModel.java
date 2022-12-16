package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.main.model.entities.LearnDto;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class LearningViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<List<LearnDto>> learnData = new MutableLiveData<>();

    public void observeLearnData(LifecycleOwner l, Observer<List<LearnDto>> o){
        learnData.observe(l, o);
    }

    public void getLearnings(){
        api.getLearnings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<List<LearnDto>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull List<LearnDto> learnDtos) {
                    learnData.postValue(learnDtos);
                }

                @Override
                public void onError(@NonNull Throwable e) {}
            });
    }

}
