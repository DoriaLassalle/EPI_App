package com.example.epi_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.R
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_team.*
import java.lang.Exception

class TeamFragment : Fragment() {

    private val myViewModel:EpiViewModel by activityViewModels()

    lateinit var myAdapter2: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter2 = TeamAdapter()

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

        botMailTo.setOnClickListener {
            val recipient="sharon.lassalle@gmail.com"
            sendEmail(recipient)
        }

        val recyclerView=recyclerTeam
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=myAdapter2
        myAdapter2.updateAdapter(myViewModel.getDataTeam())

    }

    fun sendEmail(recipient: String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.data= Uri.parse("mailto:")
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

        try {
            startActivity(intent)
        }catch (e: Exception){
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }


}