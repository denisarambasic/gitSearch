package com.algebra.mobiletaskmvvm.search_screen

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.algebra.mobiletaskmvvm.R
import com.algebra.mobiletaskmvvm.databinding.RepositoryBinding
import com.algebra.mobiletaskmvvm.models.Repository
import com.bumptech.glide.Glide

class RepositoryAdapter : RecyclerView.Adapter<RepositoryHolder>() {
    var repositories : List<Repository> = mutableListOf<Repository>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding : RepositoryBinding = DataBindingUtil
                        .inflate(layoutInflater, R.layout.repository, parent, false)
        return RepositoryHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.bind(repositories[position])
        if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#F7F7F7"))
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }
}

class RepositoryHolder(private var binding: RepositoryBinding, private var context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository) {
        Glide.with(context).load(repository.authorThumbnail).into(binding.avatarImg)
        binding.repositoryNameText.text = repository.repositoryName
        binding.authorNameText.text     = repository.authorName
        binding.watchersText.text       = repository.watchers.toString()
        binding.forksText.text          = repository.forks.toString()
        binding.issuesText.text         = repository.openIssues.toString()

        //setup click on image redirect to User detail screen
        binding.avatarImg.setOnClickListener {
            Navigation.findNavController(it).navigate(SearchScreenFragmentDirections.actionSearchScreenFragmentToUserDetailsFragment(repository.authorName, repository.repositoryName))
        }

        //setup click on repository content redirect you to Repository detail screen
        binding.rightItemPart.setOnClickListener {
            Navigation.findNavController(it).navigate(SearchScreenFragmentDirections.actionSearchScreenFragmentToRepositoryDetailsFragment(repository.authorName, repository.repositoryName))
        }
    }

}