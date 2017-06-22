package com.tlnacl.reactiveapp.ui.shop

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.tlnacl.reactiveapp.Constants
import com.tlnacl.reactiveapp.R
import com.tlnacl.reactiveapp.businesslogic.model.Product

/**
 * Created by tomt on 21/06/17.
 */
class SearchAdapter(private val context: Context,private val callback: ViewHolder.ProductClickedListener): RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    private var products: List<Product> = emptyList()

    fun setProducts(products:List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun getItemCount()= products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(context, parent, callback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position])

    open class ViewHolder(context: Context, parent: ViewGroup, val callback: ProductClickedListener)
        : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_normal, parent, false)) {

        @BindView(R.id.productImage) lateinit var image: ImageView
        @BindView(R.id.productName) lateinit var name: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(product: Product){
            Glide.with(itemView.context)
                    .load<Any>(Constants.BASE_IMAGE_URL + product.image)
                    .centerCrop()
                    .into(image)
            name.setText(product.name)
            itemView.setOnClickListener { callback.onProductClicked(product) }
        }

        interface ProductClickedListener {
            fun onProductClicked(product: Product)
        }
    }
}

