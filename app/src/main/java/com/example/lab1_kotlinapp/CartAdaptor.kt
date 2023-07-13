package com.example.lab1_kotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(private val cartList: ArrayList<Book>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartBookName: TextView = itemView.findViewById(R.id.cartBookName)
        val cartBookRole: TextView = itemView.findViewById(R.id.cartBookRole)
        val cartImageView: ImageView = itemView.findViewById(R.id.cartImageView)

        fun bind(Book: Book) {
            cartBookName.text = Book.title
            cartBookRole.text = Book.author
            // Load the photo image using Glide
            Glide.with(itemView)
                .load(Book.imageLink)
                .into(cartImageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_row_layout, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

}