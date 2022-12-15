package ru.mpei.profcom.main.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import ru.mpei.profcom.main.model.entities.OrgDto;

public class OrgViewModel extends ViewModel {

    private final MutableLiveData<OrgDto> currentOrgData = new MutableLiveData<>();

    public void observeCurrentOrgData(LifecycleOwner l, Observer<OrgDto> o){
        currentOrgData.observe(l, o);
    }

    public void postCurrentOrgData(OrgDto orgDto){
        currentOrgData.postValue(orgDto);
    }
}
