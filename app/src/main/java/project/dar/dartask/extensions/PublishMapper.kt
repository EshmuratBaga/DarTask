package project.dar.dartask.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import project.dar.dartask.model.Outcome


fun <T> PublishSubject<T>.toLiveData() : LiveData<T> {
    val data = MutableLiveData<T>()
    this.subscribe { t: T -> data.value = t}
    return data
}

fun <T> PublishSubject<Outcome<T>>.loading(isLoading: Boolean) {
    this.onNext(Outcome.loading(isLoading))
}

fun <T> PublishSubject<Outcome<T>>.success(t: T) {
    with(this){
        loading(false)
        onNext(Outcome.success(t))
    }
}

fun <T> PublishSubject<Outcome<T>>.failed(e: Throwable) {
    with(this){
        loading(false)
        onNext(Outcome.failure(e))
    }
}