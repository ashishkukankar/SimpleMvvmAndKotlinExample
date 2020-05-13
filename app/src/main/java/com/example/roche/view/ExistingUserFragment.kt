package com.example.roche.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roche.R
import com.example.roche.model.CreateUserModelView
import com.example.roche.utils.Utils
import kotlinx.android.synthetic.main.layout_viewuser.*


private val USER_PROFILE = "USER PROFILE"

class ExistingUserFragment : Fragment(),clickPocListener {

    lateinit var mView:View
    lateinit var viewModel:ViewModel
    lateinit var mRecycleView:RecyclerView
    lateinit var mProgressBar:ProgressBar
    lateinit var mContext:Context
    lateinit var mRecyclerAdapter: PosRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = this!!.context!!
        mView = inflater.inflate(R.layout.layout_viewuser, container, false);
        viewModel = ViewModelProvider(requireActivity()).get(CreateUserModelView::class.java)
        mProgressBar = mView.findViewById(R.id.progress_existingScreen)
        mRecycleView = mView.findViewById(R.id.recycleView)
        val mlinearLayout = LinearLayoutManager(mContext)
        mlinearLayout.orientation = LinearLayoutManager.VERTICAL
        mRecycleView.layoutManager = mlinearLayout
       if( Utils.checkNetworkConnectivity(mContext)) {
           mProgressBar.visibility = View.VISIBLE
           (viewModel as CreateUserModelView).getUsers().observe(this, Observer {
               mProgressBar.visibility = View.GONE
               mRecyclerAdapter = PosRecyclerAdapter(it, this)
               recycleView.adapter = mRecyclerAdapter
               val itemDecor = DividerItemDecoration(mContext, VERTICAL)
               mRecycleView.addItemDecoration(itemDecor)
           })
       }else{
           showAlertMessage("Wifi is not available")
       }
        return mView
    }

    override fun onItemClicListener(item: Int) {
        var fragment = UserDetailFragment()
        var bundle = Bundle()
        bundle.putString("Id",(viewModel as CreateUserModelView).getUsers().value!!.data[item].userId)
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(android.R.id.content, fragment)
            ?.addToBackStack(USER_PROFILE)?.commit()

    }

    fun showAlertMessage(message:String){
        val builder1: AlertDialog.Builder = AlertDialog.Builder(mContext)
        builder1.setMessage(message)
        builder1.setCancelable(true)

        builder1.setNegativeButton(
            "Close",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}
