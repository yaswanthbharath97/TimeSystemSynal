package com.reveille.timesystem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(private var productList:List<ResponseModel>):RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder> ()
{




    inner  class ProductViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView)
    {
        private  val titleView:TextView=itemView.findViewById(R.id.title_name)
        private  val imageView:ImageView=itemView.findViewById(R.id.imageView)
        private  val descriptionView:TextView=itemView.findViewById(R.id.description)
        private val countView:TextView=itemView.findViewById(R.id.count)
        private val priceView:TextView=itemView.findViewById(R.id.price_value)
        private val ratingView:TextView=itemView.findViewById(R.id.rating_rate)
        private val categoryView:TextView=itemView.findViewById(R.id.category)

      fun bind(responseModel:ResponseModel)
      {
          titleView.text=responseModel.title
          descriptionView.text=responseModel.description
         countView.text=responseModel.rating.count.toString()
          ratingView.text=responseModel.rating.rate.toString()
          priceView.text=responseModel.price.toString()
          categoryView.text=responseModel.category
          Picasso.get().load(responseModel.image).into(imageView)

      }

    }
     @SuppressLint("NotifyDataSetChanged")
     fun setProductList(products:List<ResponseModel>){
         productList=products
         notifyDataSetChanged()
     }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder
    {
      val v= LayoutInflater.from(parent.context).inflate(R.layout.activity_main2,parent,false)
        return  ProductViewHolder(v)

    }

    override fun getItemCount(): Int {
     return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=productList[position]
        holder.bind(product)
    }




}