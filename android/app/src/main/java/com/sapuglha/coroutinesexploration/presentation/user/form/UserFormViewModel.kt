package com.sapuglha.coroutinesexploration.presentation.user.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapuglha.coroutinesexploration.domain.type.User
import com.sapuglha.coroutinesexploration.domain.usecase.user.SaveUserUseCase
import com.sapuglha.coroutinesexploration.presentation.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class UserFormViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
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
            val success = saveUserUseCase.execute(inputData)
            _formSaved.postValue(Event(success))
        }
    }

    private fun getInputData(): User {
        return User(
            username = usernameField.value ?: "",
            firstname = firstnameField.value ?: "",
            lastname = lastnameField.value ?: ""
        )
    }

    private fun isFormInvalid(input: User): Boolean {
        var response = false

        usernameFieldError.value = ""
        firstnameFieldError.value = ""
        lastnameFieldError.value = ""

        if (input.username.isBlank()) {
            usernameFieldError.postValue("Required field")
            response = response || true
        }

        if (input.firstname.isBlank()) {
            firstnameFieldError.postValue("Required field")
            response = response || true
        }

        if (input.lastname.isBlank()) {
            lastnameFieldError.postValue("Required field")
            response = response || true
        }

        return response
    }

    fun setUserId(id: String?) {
        userId.value = id
    }
}
