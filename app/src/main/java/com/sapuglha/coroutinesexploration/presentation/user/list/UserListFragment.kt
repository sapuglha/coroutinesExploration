package com.sapuglha.coroutinesexploration.presentation.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.FragmentUserListBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModelUser: UserListViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.viewModel = viewModelUser
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup the recyclerView list
        binding.listUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.listUsers.hasFixedSize()
        binding.listUsers.adapter = ListRowAdapter()

        binding.floatingActionButton.setOnClickListener {
            // TODO: convert to navargs
//            startActivity(Intent(this, UserFormFragment::class.java))
        }
    }
}
