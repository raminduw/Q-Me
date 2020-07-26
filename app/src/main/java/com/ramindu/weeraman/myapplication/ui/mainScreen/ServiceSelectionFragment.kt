package com.ramindu.weeraman.myapplication.ui.mainScreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ramindu.weeraman.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_service_selection.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ServiceSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnTicket.setOnClickListener {
            findNavController().navigate(R.id.action_ServiceSelectionFragment_to_CreateEventFragment)
        }

        btnAttendence.setOnClickListener {
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        btnFeedback.setOnClickListener {
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }
}
