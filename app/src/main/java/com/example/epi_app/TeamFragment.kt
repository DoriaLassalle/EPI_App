package com.example.epi_app

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.team_list.view.*

class TeamFragment : Fragment() {

    lateinit var myAdapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter = TeamAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

     return inflater.inflate(R.layout.fragment_team, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botTeamHome.setOnClickListener {
            findNavController().navigate(R.id.action_teamFragment_to_HomeFragment)
        }


        val recyclerView=recyclerTeam
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=myAdapter
        myAdapter.updateAdapter(getData())

    }

    fun getData():List<Team>{

        val teamList:ArrayList<Team> = ArrayList()

        teamList.add(Team("Sharon Lassalle", "Master Profe", R.drawable.sha, "Bla bla bla bla bla "))
        teamList.add(Team("Tomás Van Cauwelaert", "Profe", R.drawable.tom, "bla bla"))
        teamList.add(Team("Jean-Pierre Lassalle", "Profe", R.drawable.jp, "bla bla bla"))

        return teamList

    }

}