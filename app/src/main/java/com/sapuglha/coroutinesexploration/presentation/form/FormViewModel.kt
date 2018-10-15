package com.sapuglha.coroutinesexploration.presentation.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import com.sapuglha.coroutinesexploration.presentation.Event
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class FormViewModel @Inject constructor(
    val db: AppDatabase
) : ViewModel() {

    private val _formSaved = MutableLiveData<Event<Boolean>>()
    val formSaved: LiveData<Event<Boolean>>
        get() = _formSaved

    val usernameField = MutableLiveData<String>()
    val firstnameField = MutableLiveData<String>()
    val lastnameField = MutableLiveData<String>()

    fun addUser() {
        val inputData = getInputData()

        if (isFormInvalid(inputData)) return

        db.userDao().insert(inputData)

        _formSaved.postValue(Event(true))
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
}
