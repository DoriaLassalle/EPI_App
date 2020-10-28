package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.netw.PonyApi
import com.example.epi_app.model.network.Hit
import com.example.epi_app.model.network.Pony
import com.example.epi_app.model.network.PonyRetrofitClient
import kotlinx.android.synthetic.main.fragment_pony.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PonyFragment : Fragment() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pony, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val instancia: PonyApi =PonyRetrofitClient.retrofitInstance()
        val call: Call<Pony>

        val key=Pair("key", "18827506-60fd5dd04e7ef2793d6e76d76")
        val param= Pair("q", "pony")
        val map = mapOf(key, param)

        call=instancia.fetchAllPony(map)

        call.enqueue(object : Callback<Pony>{
            override fun onResponse(call: Call<Pony>, response: Response<Pony>) {

                val pony= response.body()
                if( pony != null)
                    Log.d("foto", pony.hits.get(0).likes.toString())


            }

            override fun onFailure(call: Call<Pony>, t: Throwable) {
                Log.e("falla", t.message.toString())
            }


        })



        botPonyHome.setOnClickListener {
            findNavController().navigate(R.id.action_ponyFragment_to_HomeFragment)
        }
    }
}