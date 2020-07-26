package com.ramindu.weeraman.myapplication.ui.createEvent

import androidx.lifecycle.Observer
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramindu.weeraman.domain.*
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.myapplication.EVENT_CODE
import com.ramindu.weeraman.myapplication.PRINT_MESSAGE
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.utils.DialogHelperUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_event.*
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CreateEventFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private val createEventViewModel: CreateEventViewModel by viewModels()
    @Inject
    lateinit var dialogHelperUtil: DialogHelperUtil

    private var settingStartTime: Boolean = false

    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0

    private var selectedDay: Int = 0
    private var selectedMonth: Int = 0
    private var selectedYear: Int = 0
    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreateEvent.setOnClickListener {
            createEvent()
        }

        startLayout.setOnClickListener {
            settingStartTime = true
            showDatePicker()
        }

        endLayout.setOnClickListener {
            settingStartTime = false
            showDatePicker()
        }

        checkBoxUnlimitedTime.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                constraintLayoutDateTime.visibility = GONE
                createEventViewModel.resetEventDate()
            } else {
                constraintLayoutDateTime.visibility = VISIBLE
            }
        }

        checkBoxUnlimitedTicket.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                txtInputTicketCountLayout.visibility = GONE
            } else {
                txtInputTicketCountLayout.visibility = VISIBLE
            }
            txtInputTicketCount.text?.clear()
        }

        createEventViewModel.createEventStatus.observe(viewLifecycleOwner, Observer {

            it.fold(ifLeft = { error ->
                showToastMessage(error)
            }, ifRight = { event ->
                val bundle = bundleOf(PRINT_MESSAGE to txtInputDescription.text.toString(), EVENT_CODE to event.eventCode)
                findNavController().navigate(R.id.action_CreateEventFragment_to_QRCodeGenerateFragment, bundle)
            })

        })

        createEventViewModel.startDateLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                startTxt.text = "Please select"
            } else {
                startTxt.text = it
            }
        })

        createEventViewModel.endDateLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                endTxt.text = "Please select"
            } else {
                endTxt.text = it
            }
        })

        createEventViewModel.createEventProgressStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialogHelperUtil.showProgressBar(activity)
            } else {
                dialogHelperUtil.hideProgressBar()
            }
        })
    }

    private fun showDatePicker() {
        resetDate()
        val calendar: Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        val datePickerDialog =
            DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedDay = dayOfMonth
        selectedYear = year
        selectedMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            requireContext(), this, hour, minute, true
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        selectedHour = hourOfDay
        selectedMinute = minute
        setDateTime()
    }

    private fun showToastMessage(errorCode: ErrorCode) {
        val message = when (errorCode.code) {
            INVALID_INPUT_NAME -> "Name empty"
            INVALID_INPUT_DESCRIPTION -> "Description empty"
            INVALID_INPUT_DATE_TIME -> "Start/End date wrong"
            INVALID_TICKET_COUNT -> "Ticket count empty"
            NETWORK_ERROR -> "Network error"
            NETWORK_EXCEPTION -> "Network error"
            else -> "Error"
        }
        dialogHelperUtil.showToast(requireContext(),message)
    }

    private fun setDateTime() {
        createEventViewModel.setEventDate(selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute, settingStartTime)
    }

    private fun createEvent() {
        createEventViewModel.createEvent(
            txtInputName.text.toString(),
            description = txtInputDescription.text.toString(),
            clientNameMandatory = checkBoxClientName.isChecked,
            isUnlimitedTime = checkBoxUnlimitedTime.isChecked,
            isUnlimitedNumbers = checkBoxUnlimitedTicket.isChecked,
            isAutomaticallyCancel = checkBoxCancelDate.isChecked,
            numberOfTickets = txtInputTicketCount.text.toString()
        )
    }

    private fun resetDate() {
        selectedDay = 0
        selectedMonth = 0
        selectedYear = 0
        selectedHour = 0
        selectedMinute = 0
    }

}
