package ru.mpei.profcom.main.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import ru.mpei.profcom.main.model.entities.EventDto
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.mpei.profcom.MainActivity
import ru.mpei.profcom.network.Api
import ru.mpei.profcom.network.NetworkClient
import java.util.ArrayList

class EventsViewModel : ViewModel() {

    private val api = NetworkClient.createApi(Api::class.java)

    private val eventsData = MutableLiveData<ArrayList<EventDto>>()

    fun observeEventsData(l: LifecycleOwner?, o: Observer<ArrayList<EventDto>>?) {
        eventsData.observe(l!!, o!!)
    }

    fun getEvents(id: Int) = api.getEvents(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { eventDtos ->
            eventsData.postValue(eventDtos as ArrayList<EventDto>)
        }

    fun addEventToGoing(eventId: Int) = api
        .addEventToGoing(MainActivity.prefs.getInt("id", -1), eventId)
}