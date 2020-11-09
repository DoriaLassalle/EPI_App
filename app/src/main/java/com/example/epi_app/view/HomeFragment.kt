package com.example.epi_app.view

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.epi_app.R
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()

    private var token1: String=""
    private val ADMIN="admin@.com"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recibo el nombre del usuario que ingresó a la app-picked at loginfragment
        myViewModel.selectedRecibir.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            token1 = it

            if (ADMIN==it){


                botAdmin.visibility=View.VISIBLE
            }

        })


        cvClase.setOnClickListener {


            findNavController().navigate(R.id.action_HomeFragment_to_reservarClaseFragment)
        }

        cvGallery.setOnClickListener {

            findNavController().navigate((R.id.action_HomeFragment_to_epiPonyFragment))
        }


        cvPony.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_ponyFragment)
        }

        cvTienda.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_tiendaFragment)
        }
        Log.e("que tiene", token1)
        Log.e("que tiene2", ADMIN)



        botAdmin.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_adminFragment)


        }

        cvNosotros.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_teamFragment)
        }

        ivInstagram.setOnClickListener {

            val appInst = "http://instagram.com/escuela_de_ponies"
            val urlInst = "http://instagram.com/_u/escuela_de_ponies"
            sendToInstagram(appInst, urlInst)

        }

        ivFacebook.setOnClickListener {
            val appFb="fb://page/1293120265"//cambiar este id por el de la escuela
            val urlFb="https://www.facebook.com/escueladeponies"
            sendToFacebook(appFb,urlFb)

        }


    }

    fun sendToInstagram(appInst: String, urlInst: String) {
        try {
            val intentInst = Intent(Intent.ACTION_VIEW)
            intentInst.data = Uri.parse(appInst)
            intentInst.setPackage("com.instagram.android")
            startActivity(intentInst)
        }
        catch (anfe: ActivityNotFoundException)
        {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(urlInst)
                )
            )
        }
    }

    fun sendToFacebook(appFb: String,urlFb: String ){

        try {
            if (/*context?.let { isAppInstalled(it, "com.facebook.orca") }!! || isAppInstalled(
                    requireContext(), "com.facebook.katana")
                || isAppInstalled(requireContext(), "com.example.facebook") ||*/ context?.let {
                    isAppInstalled(it, "com.facebook.android")
                }!!
            ) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(appFb)))
            } else {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun isAppInstalled(context: Context, packageName: String?): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName!!, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}