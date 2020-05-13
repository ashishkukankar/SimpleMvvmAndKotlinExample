package com.example.roche.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roche.R
import com.example.roche.model.CreateUserModelView


class ExistingUserFragment : Fragment() {

    lateinit var mView:View
    lateinit var viewModel:ViewModel
    lateinit var mRecycleView:RecyclerView
    var mContext = getContext()
    lateinit var mRecyclerAdapter: PosRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.layout_viewuser, container, false);
        viewModel = ViewModelProvider(requireActivity()).get(CreateUserModelView::class.java)

        mRecycleView = mView.findViewById(R.id.recycleView)
        val mlinearLayout = LinearLayoutManager(mContext)
        mlinearLayout.orientation = LinearLayoutManager.VERTICAL
        mRecycleView.layoutManager = mlinearLayout
        //val itemDecor = DividerItemDecoration(mContext, HORIZONTAL)
        //mRecycleView.addItemDecoration(itemDecor)

        (viewModel as CreateUserModelView).getUsers().observe(this, Observer {
            mRecyclerAdapter = PosRecyclerAdapter(it)
        })
        return mView
    }
}
