package ru.mpei.profcom.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.mpei.profcom.MainActivity
import ru.mpei.profcom.main.model.entities.GratitudeDto
import ru.mpei.profcom.network.Api
import ru.mpei.profcom.network.NetworkClient

class GratidudesViewModel: ViewModel() {

    private val api = NetworkClient.createApi(Api::class.java)

    private val _gratitudes = MutableLiveData<List<GratitudeDto>>()
    val gratitudes: LiveData<List<GratitudeDto>> = _gratitudes

    fun getGratitudes() = api.getGratitudes(MainActivity.prefs.getInt("id", -1))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            _gratitudes.postValue(it)
        }, {})


}