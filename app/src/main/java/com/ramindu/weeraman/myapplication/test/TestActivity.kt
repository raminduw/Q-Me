package com.ramindu.weeraman.myapplication.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramindu.weeraman.myapplication.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.test_dialog.view.*
import kotlin.random.Random

class TestActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mmvFilterAdapter: ContentAdapter

    lateinit var mmvFilterAdapter2: ContentAdapter2

    lateinit var mmvFilterAdapter3: ContentAdapter3

    lateinit var myPresenter: MyPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        myPresenter = MyPresenter()
        setupRecyclerView()

        switch_id.setOnClickListener(this)

        /* mmvFilterAdapter.submitList( myPresenter.getValues())
         mmvFilterAdapter.getSections()*/

        switch_id.isChecked = true
    }

    private fun setupRecyclerView() {
        val list = mutableListOf<Content>()
        val rand = Random(System.currentTimeMillis())
        list.add(Content(1, "AA", rand.nextBoolean()))
        list.add(Content(2, "AA", rand.nextBoolean()))
        list.add(Content(3, "AA", rand.nextBoolean()))
        list.add(Content(4, "BA", rand.nextBoolean()))
        /* list.add(Content(5, "AA", rand.nextBoolean()))
         list.add(Content(6, "AA", rand.nextBoolean()))
         list.add(Content(7, "CA", rand.nextBoolean()))
         list.add(Content(8, "AA", rand.nextBoolean()))
         list.add(Content(9, "DA", rand.nextBoolean()))
         list.add(Content(10, "EA", rand.nextBoolean()))
         list.add(Content(11, "FA", rand.nextBoolean()))
         list.add(Content(12, "GA", rand.nextBoolean()))*/


        /* mmvFilterAdapter = ContentAdapter(object : ItemActionListener {
             override fun onItemClick(item: Content) {
                 mmvFilterAdapter.submitList( myPresenter.getValues())
             }

             override fun checkItem(item: Content, isSelected: Boolean) {
                 myPresenter.changeMe(item, isSelected)
                 mmvFilterAdapter.submitList( myPresenter.getValues())
             }
         })

         val layoutManager = LinearLayoutManager(this)

         myEventList?.apply {
             adapter = mmvFilterAdapter
             setLayoutManager(layoutManager)
         }*/

        mmvFilterAdapter3 = ContentAdapter3(object : ItemActionListener2 {

            override fun onItemClicked(item: Content) {
                TODO("Not yet implemented")
            }

            override fun checkItem(item: Content, isSelected: Boolean) {
                myPresenter.changeMe(item, isSelected)
                mmvFilterAdapter3.submitList(list)
            }
        })

        val layoutManager = LinearLayoutManager(this)

        myEventList?.apply {
            adapter = mmvFilterAdapter3
            setLayoutManager(layoutManager)
        }

        mmvFilterAdapter3.submitList(list)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.switch_id -> {
               // Log.d("TAG", "########## ON CLICKED" +switch_id.isChecked)
                test()
            }
        }
    }


    private fun test(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.test_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Login Form")
        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.dialogLoginBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val name = mDialogView.dialogNameEt.text.toString()
            val email = mDialogView.dialogEmailEt.text.toString()
            val password = mDialogView.dialogPasswEt.text.toString()
            //set the input text in TextView
           // mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
        }
        //cancel button click of custom layout
        mDialogView.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

}