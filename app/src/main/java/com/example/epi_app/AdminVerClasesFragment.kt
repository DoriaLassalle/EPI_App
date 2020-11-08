package com.example.epi_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin_ver_clases.*

class AdminVerClasesFragment : Fragment(), AdminVerClasesAdapter.AdminSelClass {

    private val myViewModel: EpiViewModel by activityViewModels()
    lateinit var myAdpater6: AdminVerClasesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdpater6= AdminVerClasesAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_ver_clases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botAdminBack2.setOnClickListener {
            findNavController().navigate(R.id.action_adminClaseCnAlumnosFragment_to_adminClaseFragment)
        }

        val otroRecycler=recyclerAdminVerClases
        otroRecycler.layoutManager=LinearLayoutManager(context)
        otroRecycler.adapter=myAdpater6

        myViewModel.getAllClases().observe(viewLifecycleOwner, Observer {
            myAdpater6.updteAdpater(it)
        })



    }

    override fun showDialog(adminClase: ClaseEntity) {
        Log.d("trae la clase?",adminClase.toString())

        val claseToDelete=adminClase.id


        val managerDialog: AlertDialog? =activity?.let {
            val builder=AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.modificar_clase, DialogInterface.OnClickListener{
                    dialog, id->

                    findNavController().navigate(R.id.action_adminClaseCnAlumnosFragment_to_adminClaseFragment)

                    //FALTA TRAER EL ID DE LA CLASE A MODIFICAR...

                })
                setNegativeButton(R.string.cancelar,
                    DialogInterface.OnClickListener { dialog, id ->

                    })

                setNeutralButton(R.string.eliminar_clase,
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(context, "CLASE ELIMINADA", Toast.LENGTH_SHORT).show()

                        myViewModel.deleteClase(claseToDelete)

                    })

            }
            builder.setTitle(R.string.titulo_dialog)
            builder.setMessage(R.string.msg_dialog)
            builder.create()
            builder.show()

        }

    }
}