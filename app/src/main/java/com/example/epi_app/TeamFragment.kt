package com.example.epi_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_team.*

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

        teamList.add(Team("SHARON LASSALLE C.", "Master Profe", R.drawable.sha, "Equitadora de no se cuando," +
                " Domadora de potros chúcaros y tierna profe de niñitos "))
        teamList.add(Team("TOMÁS VAN CAUWELAERT", "Profe", R.drawable.tom, "bla bla"))
        teamList.add(Team("JEAN-PIERRE LASSALLE T.", "Profe", R.drawable.jp, "bla bla bla"))

        return teamList

    }

}