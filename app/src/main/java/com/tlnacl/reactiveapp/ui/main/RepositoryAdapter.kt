package com.tlnacl.reactiveapp.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.tlnacl.reactiveapp.R
import com.tlnacl.reactiveapp.Repository

/**
 * Created by tomt on 29/05/17.
 */
class RepositoryAdapter(private val context: Context):RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(){
    private var repositories: List<Repository> = emptyList()

    fun setRepositories(repositories:List<Repository>){
        this.repositories = repositories
        notifyDataSetChanged()
    }

    override fun getItemCount()= repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(context, parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repositories[position])

    class ViewHolder(context: Context, parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_normal, parent, false)) {

        @BindView(R.id.tvName) lateinit var tvName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(repository:Repository){
            tvName.text = repository.name
        }
    }
}

