package com.sapuglha.coroutinesexploration.presentation.form

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.ActivityFormBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FormViewModel by lazy {
        ViewModelProviders.of(this, factory).get(FormViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.formSaved.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, "User added", Toast.LENGTH_LONG).show()
                Timber.e("*** Toast showed ***")
                finish()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_save) {
            viewModel.addUser()
            true
        } else super.onOptionsItemSelected(item)

    }
}
