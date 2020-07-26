package com.ramindu.weeraman.myapplication.ui.user.register

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.utils.DialogHelperUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var dialogHelperUtil: DialogHelperUtil

    private val userRegisterViewModel: UserRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRegister.setOnClickListener {
            userRegisterViewModel.userRegister(userName = email.text.toString(), password = password.text.toString(), confirmPassword = passwordConfirm.text.toString())
        }

        userRegisterViewModel.registerResultLiveData.observe(viewLifecycleOwner, Observer {
            it.fold(ifLeft = { error ->
                dialogHelperUtil.showToast(context, "Error")
            }, ifRight = { result ->
                findNavController().navigate(R.id.action_RegisterFragment_to_Login)
            })
        })

        userRegisterViewModel.registerStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialogHelperUtil.showProgressBar(activity)
            } else {
                dialogHelperUtil.hideProgressBar()
            }
        })
    }

}
