package com.sapuglha.coroutinesexploration.presentation.user.form

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.FragmentUserFormBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class UserFormFragment : Fragment() {
    private lateinit var binding: FragmentUserFormBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModelUser: UserFormViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_form, container, false)
        binding.viewModel = viewModelUser
        binding.lifecycleOwner = this

        viewModelUser.formSaved.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), "User added", Toast.LENGTH_LONG).show()
                Timber.e("*** Toast showed ***")
                // TODO : close fragment
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_save) {
            viewModelUser.addUser()
            true
        } else super.onOptionsItemSelected(item)
    }
}
