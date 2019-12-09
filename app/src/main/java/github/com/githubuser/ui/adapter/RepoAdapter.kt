package github.com.githubuser.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import github.com.githubuser.R
import github.com.githubuser.model.Repo
import kotlinx.android.synthetic.main.repo_list_item.view.*

class RepoAdapter(var repoList: List<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoHolder>() {


    inner class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtRepoName: AppCompatTextView = itemView.txt_repo_name
        val txtRepoDesc: AppCompatTextView = itemView.txt_repo_desc
        val btnRepoUrl: Button = itemView.btn_repo_url


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        return RepoHolder(view)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        val repo = this.repoList[position]
        holder.txtRepoName.text = repo.repoName
        holder.txtRepoDesc.text = repo.repoDesc

        holder.btnRepoUrl.setOnClickListener {
            val browser =
                Intent(Intent.ACTION_VIEW, Uri.parse(repo.repoUrl))
            it.context.startActivity(browser)

        }
    }


}