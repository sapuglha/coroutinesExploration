package com.sapuglha.coroutinesexploration.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.ActivityListBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import com.sapuglha.coroutinesexploration.presentation.form.FormActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, factory).get(ListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        // Setup the recyclerView list
        binding.listUsers.layoutManager = LinearLayoutManager(this)
        binding.listUsers.hasFixedSize()
        binding.listUsers.adapter = ListRowAdapter()

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }
}
