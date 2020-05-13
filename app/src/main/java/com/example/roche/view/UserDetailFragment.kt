package com.example.roche.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roche.R
import com.example.roche.model.CreateUserModelView
import com.example.roche.utils.Utils

class UserDetailFragment: Fragment() {

    lateinit var mContext:Context
    lateinit var viewModel:ViewModel
    lateinit var mView:View
    lateinit var userName:EditText
    lateinit var userId:EditText
    lateinit var userRole:EditText
    lateinit var userDob:EditText
    lateinit var progress:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = getArguments()?.getString("Id")
        mView = inflater.inflate(R.layout.layout_userdetail, container, false);
        mContext = this!!.context!!
        viewModel = ViewModelProvider(requireActivity()).get(CreateUserModelView::class.java)
        userId = mView.findViewById(R.id.useridedit)
        userName = mView.findViewById(R.id.usernameedit)
        userRole =  mView.findViewById(R.id.userRoleTypeedit)
        userDob  =  mView.findViewById(R.id.userDateofbirthedit)
        progress = mView.findViewById(R.id.progressuser)
        if(Utils.checkNetworkConnectivity(mContext)){
            progress.visibility = View.VISIBLE
            id?.let {
                (viewModel as CreateUserModelView).userdata(it).observe(this, Observer {
                    progress.visibility = View.GONE
                    userId.setText(it.data.userId)
                    userName.setText("${it.data.firstName} ${it.data.lastName}")
                    userRole.setText(it.data.userRole)
                    userDob.setText(it.data.dob)
                })
            }
        }else{
            showAlertMessage("Wifi is not available")
        }
        return mView
    }

    fun showAlertMessage(message:String){
        val builder1: AlertDialog.Builder = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(true)

        builder1.setNegativeButton(
            "Close",
            { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}