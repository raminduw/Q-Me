package com.ramindu.weeraman.myapplication.ui.manageEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramindu.weeraman.domain.entities.EventModel
import com.ramindu.weeraman.myapplication.EVENT_CODE
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.utils.DialogHelperUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class EventListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var eventAdapter: EventsAdapter

    private val eventListViewModel: EventListViewModel by viewModels()

    @Inject
    lateinit var dialogHelperUtil: DialogHelperUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.myEventList)

       // eventListViewModel.getEventList()

        eventListViewModel.getEvents()

        eventAdapter = EventsAdapter(mutableListOf(), this::startDetailPage)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        recyclerView.adapter = eventAdapter


        /*eventListViewModel.eventsLiveData.observe(viewLifecycleOwner, Observer {
            it.fold(ifLeft = {
                dialogHelperUtil.showToast(context, "Error")
            }, ifRight = { events ->
                eventAdapter.setData(events)
            })
        })

        eventListViewModel.eventListStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialogHelperUtil.showProgressBar(activity)
            } else {
                dialogHelperUtil.hideProgressBar()
            }
        })*/

        eventListViewModel._eventList.observe(viewLifecycleOwner, Observer {
            eventAdapter.addItem(it)
        })

    }

    private fun startDetailPage(eventModel: EventModel) {
        val bundle = bundleOf(EVENT_CODE to eventModel.eventCode)
        findNavController().navigate(R.id.action_EventListFragment_to_EventDetailsFragment, bundle)
    }
}
