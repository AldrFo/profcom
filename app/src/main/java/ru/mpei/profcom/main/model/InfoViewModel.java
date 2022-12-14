package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mpei.profcom.main.model.entities.InfoDto;

public class InfoViewModel extends ViewModel {

    private MutableLiveData<InfoDto> infoData = new MutableLiveData<>();
    private MutableLiveData<List<InfoDto>> infoDataList = new MutableLiveData<>();

    public void observeInfoData(LifecycleOwner l, Observer<InfoDto> o){
        infoData.observe(l, o);
    }

    public void observeInfoDataList(LifecycleOwner l, Observer<List<InfoDto>> o){
        infoDataList.observe(l, o);
    }
}
