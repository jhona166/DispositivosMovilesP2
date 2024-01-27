package com.example.recyclerview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.ui.adapters.UsersAdapter
import com.example.recyclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    //Creamos una variable para la lista
    private var userList : MutableList<Users> = ArrayList<Users>()

    private lateinit var binding : ActivityMainBinding

    private var count : Int = 1

    //Creamos el adaptador como una variable de clase
    //private var usersAdapter = UsersAdapter({deleteUsers(it)}, {selectUsers(it)})

    private var userDiffAdapter = UsersAdapterDiffUtil({deleteDiffUsers(it)}, {selectUsers(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()
        initListeners()
    }

    //Inicializamos nuestro RecyclerView
    private fun initRecycleView(){

        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun initListeners(){
        //Manera Facil
        binding.btnInsert.setOnClickListener {
            val us = Users(1,
                "Jhonatan $count" ,
                "Alumno",
                "https://as1.ftcdn.net/v2/jpg/05/18/10/54/1000_F_518105489_I5rSS9oSMXURJCJQtuEbl9AEQlEPu8LZ.jpg")

            count++

            //Adapter Tradicional
            //insertUsers(us)

            //Adapter Diff
            insertUsersDiff(us)
        }
    }
    /*
        private fun insertUsers(us: Users){

            userList.add(us)
            Log.d("LST",userList.toString())
            usersAdapter.listUsers = userList

            //Notifacion Simple
            usersAdapter.notifyDataSetChanged()

            //Notifiacion de manera complementaria
            usersAdapter.notifyItemInserted(userList.size)
        }

     */

    private fun insertUsersDiff(us: Users){

        userList.add(us)
        Log.d("LST",userList.toString())
        userDiffAdapter.submitList(userList.toList())

    }
    /*
        private fun deleteUsers(position: Int){

            userList.removeAt(position)
            usersAdapter.listUsers = userList

        }

     */

    private fun deleteDiffUsers(position: Int){

        userList.removeAt(position)
        userDiffAdapter.submitList(userList.toList())

    }

    private fun selectUsers(user: Users){
        Snackbar.make(this, binding.btnInsert, user.name, Snackbar.LENGTH_LONG).show()

        /*
        val i = Intent()
        i.putExtra("usuarioId", user.id)
        startActivity(i)
         */
    }

}