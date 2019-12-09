package github.com.githubuser.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.com.githubuser.R
import github.com.githubuser.model.Repo
import github.com.githubuser.ui.adapter.RepoAdapter
import github.com.githubuser.viewModel.RepoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ReposListFragment : Fragment() {

    lateinit var repoAdapter: RepoAdapter

    private val viewModel: RepoViewModel by viewModel()

    var repoList: List<Repo>? = null

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

        viewModel.repos.observe(viewLifecycleOwner, Observer { repos ->
            println("REPOS $repos")
            repoAdapter = RepoAdapter(repos)
            rvRepoList.setHasFixedSize(true)
            rvRepoList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvRepoList.adapter = repoAdapter


        })

        viewModel.setUsername(login!!)





        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
