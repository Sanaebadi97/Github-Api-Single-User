package github.com.githubuser.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.com.githubuser.R
import github.com.githubuser.ui.adapter.RepoAdapter
import github.com.githubuser.viewModel.RepoViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.progressbar
import kotlinx.android.synthetic.main.fragment_repos_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ReposListFragment : Fragment() {

    lateinit var repoAdapter: RepoAdapter

    private val viewModel: RepoViewModel by viewModel()


    private var login: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = arguments!!.getString("login")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repos_list, container, false)
        val rvRepoList = view.findViewById<RecyclerView>(R.id.rv_repos)
        val progressbar = view.findViewById<ProgressBar>(R.id.progressbar)

        progressbar.visibility = View.VISIBLE

        viewModel.repos.observe(viewLifecycleOwner, Observer { repos ->
            println("REPOS $repos")


            repoAdapter = RepoAdapter(repos)
            rvRepoList.setHasFixedSize(true)
            rvRepoList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvRepoList.adapter = repoAdapter
            progressbar.visibility = View.GONE

        })

        viewModel.setUsername(login!!)






        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
