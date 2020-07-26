package com.ramindu.weeraman.myapplication.ui.manageEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ramindu.weeraman.domain.entities.EventDetailModel
import com.ramindu.weeraman.myapplication.EVENT_CODE
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.utils.DialogHelperUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_event_details.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private val eventDetailsViewModel: EventDetailsViewModel by viewModels()

    @Inject
    lateinit var dialogHelperUtil: DialogHelperUtil


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        eventDetailsViewModel.getEventDetails(arguments?.getString(EVENT_CODE) ?: "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventDetailsViewModel.eventLiveData.observe(viewLifecycleOwner, Observer {
            it.fold(ifLeft = { error ->
                dialogHelperUtil.showToast(context, "Error")
            }, ifRight = { eventDetails ->
                setEventDetails(eventDetails)
            })
        })

        eventDetailsViewModel.eventDetailsStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialogHelperUtil.showProgressBar(activity)
            } else {
                dialogHelperUtil.hideProgressBar()
            }
        })
    }

    private fun setEventDetails(eventDetailModel: EventDetailModel) {
        eventName.text = eventDetailModel.description
    }

}
