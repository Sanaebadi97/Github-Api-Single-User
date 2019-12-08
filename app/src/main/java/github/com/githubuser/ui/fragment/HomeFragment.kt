package github.com.githubuser.ui.fragment


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import github.com.githubuser.R
import github.com.githubuser.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()


    private var navController: NavController? = null

    private var name: String? = null
    private var bio: String? = null
    private var image: String? = null
    private var website: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        if (!isOnline()) {
            navController!!.navigate(R.id.action_homeFragment_to_networkFragment)
        }


//get data from view model
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            println("DEBUG : ${user}")
            name = user.name
            bio = user.bio
            image = user.avatarUrl
            website = user.webSite


            progressbar.visibility = View.GONE

            if (name != null && bio != null && image != null && website != null) {
                val bundle = bundleOf(
                    "name" to name,
                    "bio" to bio,
                    "image" to image, "website" to website
                )

                //navigate to details fragment with bundle
                navController!!.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            } else {

                Toast.makeText(
                    activity,
                    getString(R.string.user_not_valid),
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        })



        btn_generate.setOnClickListener {

            if (edt_user_name.text.toString().trim().isNotEmpty()) {

                progressbar.visibility = View.VISIBLE


                viewModel.setUsername(edt_user_name.text.toString())


            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.error_fill_username),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }


    //check network access
    private fun isOnline(): Boolean {
        val connectivityManager =
            activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
