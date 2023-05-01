package ru.mpei.profcom.entry.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.network.Api;
import ru.mpei.profcom.network.NetworkClient;

public class EntryViewModel extends ViewModel {

    private final Api api = NetworkClient.createApi(Api.class);

    private final MutableLiveData<UserData> entryData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registerData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> categoryData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    public MutableLiveData<Boolean> getCategoryData() {
        return categoryData;
    }

    public MutableLiveData<UserData> getEntryData() {
        return entryData;
    }

    public MutableLiveData<Throwable> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getRegisterData() {
        return registerData;
    }

    public void entry(String email, String password){
        api.entry(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(userData -> {
                entryData.postValue(userData);
                saveUserData(userData);
            }, error::postValue);
    }

    public void register(String email, String group, String profCard, String password){
        api.register(email, password, group, profCard)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(() -> registerData.postValue(true), error::postValue);
    }

    public void setUserTypeWithEntry(String email, String password, String type, int pbId) {
        api.setUserType(email, type, pbId)
                .flatMap(response -> api.entry(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( userData -> {
                    categoryData.postValue(true);
                    saveUserData(userData);
                }, error::postValue);

    }

    private void saveUserData(UserData response){
        MainActivity.prefs.edit()
                .putInt("id", response.id)
                .putString("email", response.email)
                .putString("password", response.password)
                .apply();
    }
}
