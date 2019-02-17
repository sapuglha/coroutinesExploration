package com.sapuglha.coroutinesexploration.presentation.user.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapuglha.coroutinesexploration.data.db.AppDatabase
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import com.sapuglha.coroutinesexploration.presentation.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UserFormViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {

    private val userId = MutableLiveData<String>()

    private val _formSaved = MutableLiveData<Event<Boolean>>()
    val formSaved: LiveData<Event<Boolean>>
        get() = _formSaved

    val usernameField = MutableLiveData<String>()
    val usernameFieldError = MutableLiveData<String>()
    val firstnameField = MutableLiveData<String>()
    val firstnameFieldError = MutableLiveData<String>()
    val lastnameField = MutableLiveData<String>()
    val lastnameFieldError = MutableLiveData<String>()

    fun addUser() {
        Timber.e("*** Save button pressed ***")
        val inputData = getInputData()

        if (isFormInvalid(inputData)) return

        viewModelScope.launch {
            withContext(IO) {
                db.userDao().insert(inputData)
            }

            _formSaved.postValue(Event(true))
        }
    }

    private fun getInputData(): UserEntity {
        return UserEntity(
            username = usernameField.value ?: "",
            firstName = firstnameField.value ?: "",
            lastName = lastnameField.value ?: ""
        )
    }

    private fun isFormInvalid(input: UserEntity): Boolean {
        var response = false

        usernameFieldError.value = ""
        firstnameFieldError.value = ""
        lastnameFieldError.value = ""

        if (input.username.isBlank()) {
            usernameFieldError.postValue("Required field")
            response = response || true
        }

        if (input.firstName.isBlank()) {
            firstnameFieldError.postValue("Required field")
            response = response || true
        }

        if (input.lastName.isBlank()) {
            lastnameFieldError.postValue("Required field")
            response = response || true
        }

        return response
    }

    fun setUserId(id: String?) {
        userId.value = id
    }
}
