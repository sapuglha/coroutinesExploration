package com.sapuglha.coroutinesexploration.presentation.user.form

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.FragmentUserFormBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import com.sapuglha.coroutinesexploration.presentation.extensions.hideKeyboard
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class UserFormFragment : Fragment() {
    private lateinit var binding: FragmentUserFormBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModelUser: UserFormViewModel by viewModels { viewModelFactory }

    private val arguments: UserFormFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        viewModelUser.setUserId(arguments.userId)

        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = if (arguments.userId.isNullOrBlank())
                "New User" else "User Details"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_form, container, false)
        binding.viewModel = viewModelUser
        binding.lifecycleOwner = this

        viewModelUser.formSaved.observe(this, Observer {
            it.getContentIfNotHandled()?.let { success ->
                if (success) {
                    Toast.makeText(requireContext(), "User added", Toast.LENGTH_LONG).show()
                    Timber.e("*** Toast showed ***")

                    findNavController().navigateUp()
                } else {
                    Toast.makeText(requireContext(), "Failed to save", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_form, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when (id) {
            R.id.action_save -> {
                hideKeyboard()
                viewModelUser.addUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
