package com.sapuglha.coroutinesexploration.presentation.extensions

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("fieldValidationError")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    if (errorMessage.isNullOrBlank()) {
        this.error = ""
        this.isErrorEnabled = false
    } else {
        this.error = errorMessage
        this.isErrorEnabled = true
    }
}
