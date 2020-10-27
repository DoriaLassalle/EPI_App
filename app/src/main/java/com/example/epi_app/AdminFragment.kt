package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.viewmodel.AlumnoViewModel
import kotlinx.android.synthetic.main.fragment_admin.*


class AdminFragment : Fragment() {

    lateinit var mAdapter: AdminAdapter
    val mViewModel: AlumnoViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter= AdminAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mRecycler=recyclerAdmin
        mRecycler.layoutManager=LinearLayoutManager(context)
        mRecycler.adapter=mAdapter
         mViewModel.allAlumno.observe(viewLifecycleOwner, Observer {
             mAdapter.UpdateAdapter(it)

             Log.d("lista", it.toString())
         })

    }
}