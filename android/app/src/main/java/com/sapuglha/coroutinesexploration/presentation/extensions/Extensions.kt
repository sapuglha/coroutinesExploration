package com.sapuglha.coroutinesexploration.presentation.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() =
    view?.let { activity?.hideKeyboard(it) }

fun AppCompatActivity.hideKeyboard() =
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)

fun Context.hideKeyboard(view: View) =
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
