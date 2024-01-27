package com.example.recyclerview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.ui.adapters.UsersAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    //Creamos una variable para la lista
    private var userList : MutableList<Users> = ArrayList<Users>()

    private lateinit var binding : ActivityMainBinding

    //Creamos el adaptador como una variable de clase
    private var usersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()
        initListeners()
    }

    //Inicializamos nuestro RecyclerView
    private fun initRecycleView(){

        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    private fun initListeners(){
        //Manera Facil
        binding.btnInsert.setOnClickListener {
            val us = Users(1,
                "Jhonatan",
                "Alumno",
                "https://as1.ftcdn.net/v2/jpg/05/18/10/54/1000_F_518105489_I5rSS9oSMXURJCJQtuEbl9AEQlEPu8LZ.jpg")

            userList.add(us)
            Log.d("LST",userList.toString())
            usersAdapter.listUsers = userList
            usersAdapter.notifyDataSetChanged()
        }
    }
}