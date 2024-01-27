package com.example.recyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recyclerview.R
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ItemsUsersBinding

class UsersAdapterDiffUtil (private val onDeleteItem : (Int )-> Unit,
                            private val onSelectItem: ( Users ) -> Unit) :
    ListAdapter<Users, UsersAdapterDiffUtil.ViewHolderUsers>(DiffUtilUserCallBack) {


    //Creamos una CLase ViewHolder
    class ViewHolderUsers(view: View) : RecyclerView.ViewHolder(view){

        //esta variable no necesita un lateinit para ser inicializda.
        private var binding: ItemsUsersBinding = ItemsUsersBinding.bind(view)

        //Encargada de la iteracion de cada usuario
        fun render (item: Users, onDeleteItem : (Int)-> Unit, onSelectItem: (Users ) -> Unit) {

            //Ingreso de datos que se implementaran en la Interfaz
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.desc
            binding.imgUser.load(item.img)

            binding.btnBorrar.setOnClickListener {
                onDeleteItem(adapterPosition)
            }

            binding.imgUser.setOnClickListener {
                onSelectItem(item)
            }
        }
    }

    //Implementacion de la Clases Abstractas
    //Encargada de crear el Holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {

        //Encargado del inflado de la informacion
        val inflater = LayoutInflater.from(parent.context)

        //Se tiene el control del UsersItems
        return ViewHolderUsers(inflater.inflate(R.layout.items_users, parent,false))
    }

    //Ingresa cada uno de los elemoentos de las dos clases anteriores, los une y los envia.
    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {

        holder.render(getItem(position), onDeleteItem, onSelectItem)
    }

    private object DiffUtilUserCallBack : DiffUtil.ItemCallback<Users>(){

        //Elementos que ingresan y se comparan items
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {

            return (oldItem.id == newItem.id)
        }

        //Elementos que ingresan y se comparan el contenido
        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {

            return (oldItem == newItem)
        }


    }

}