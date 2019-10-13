package com.sapuglha.coroutinesexploration.presentation.extensions

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("fieldValidationError")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    error = errorMessage ?: ""
    isErrorEnabled = errorMessage?.isNotBlank() ?: false
}
