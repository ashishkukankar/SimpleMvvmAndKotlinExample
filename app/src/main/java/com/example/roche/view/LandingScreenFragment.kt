package com.example.roche.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.roche.R

private val CREATE_USER = "Creat User"

private val EXISTING_USER = "Existing USER"

class LoginScreenFragment: Fragment(),View.OnClickListener {
    lateinit var mView:View
    lateinit var mAddUserBtn:Button
    lateinit var mExistUserBtn:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.layout_login, container, false);
        mAddUserBtn = mView.findViewById(R.id.addUser)
        mExistUserBtn = mView.findViewById(R.id.existingUser)
        mAddUserBtn.setOnClickListener(this)
        mExistUserBtn.setOnClickListener(this)
        return mView
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addUser->{activity?.
            supportFragmentManager?.
            beginTransaction()?.
            replace(android.R.id.content,CreateUserFragment())?.
            addToBackStack(EXISTING_USER)?.commit()}

            R.id.existingUser->{activity?.
            supportFragmentManager?.
            beginTransaction()?.
            replace(android.R.id.content,ExistingUserFragment())?.
            addToBackStack(EXISTING_USER)?.
            commit()}

            else-> "no fragment"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }
}