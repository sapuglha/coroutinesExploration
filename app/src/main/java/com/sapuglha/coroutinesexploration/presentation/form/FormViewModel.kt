package com.sapuglha.coroutinesexploration.presentation.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import com.sapuglha.coroutinesexploration.presentation.Event
import kotlinx.coroutines.experimental.*
import timber.log.Timber
import javax.inject.Inject

class FormViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {

    private val _formSaved = MutableLiveData<Event<Boolean>>()
    val formSaved: LiveData<Event<Boolean>>
        get() = _formSaved

    val usernameField = MutableLiveData<String>()
    val firstnameField = MutableLiveData<String>()
    val lastnameField = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun addUser() {
        Timber.e("*** Save button pressed ***")
        val inputData = getInputData()

        if (isFormInvalid(inputData)) return

        ioScope.launch {
            count()
            saveToDB(inputData)
            _formSaved.postValue(Event(true))
        }
    }

    private suspend fun count() {
        Timber.d(">=> Started counting <=<")
        var iteration = 0
        do {
            Timber.d(">=> Current iteration: $iteration <=<")
            delay(100)
            iteration++
        } while (iteration < 20)

        Timber.d(">=> Done counting <=<")
    }

    private suspend fun saveToDB(inputData: UserEntity) {
        Timber.w("=== Will save to DB ===")
        delay(5_000)
        db.userDao().insert(inputData)
        Timber.w("=== Done saving to DB ===")
    }

    private fun getInputData(): UserEntity {
        return UserEntity(
            username = usernameField.value ?: "",
            firstName = firstnameField.value ?: "",
            lastName = lastnameField.value ?: ""
        )
    }

    private fun isFormInvalid(input: UserEntity): Boolean {
        return (input.username.isBlank()
                || input.firstName.isBlank()
                || input.lastName.isBlank())
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
