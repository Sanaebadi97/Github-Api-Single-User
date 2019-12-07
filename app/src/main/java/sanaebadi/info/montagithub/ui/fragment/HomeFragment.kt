package sanaebadi.info.montagithub.ui.fragment


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.koin.android.viewmodel.ext.android.viewModel
import sanaebadi.info.montagithub.R
import sanaebadi.info.montagithub.databinding.FragmentHomeBinding
import sanaebadi.info.montagithub.viewModel.MainViewModel


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    private var navController: NavController? = null

    private lateinit var nameArg: NavArgument
    private lateinit var bioArg: NavArgument
    private lateinit var websiteArg: NavArgument
    private lateinit var imageArg: NavArgument

    private var name: String? = null
    private var bio: String? = null
    private var image: String? = null
    private var website: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        if (!isOnline()) {
            navController!!.navigate(R.id.action_homeFragment_to_networkFragment)
        }


        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            println("DEBUG : ${user}")
            name = user.name
            bio = user.bio
            image = user.avatarUrl
            website = user.webSite


            binding.progressbar.visibility = View.GONE

            val bundel = bundleOf(
                "name" to name,
                "bio" to bio,
                "image" to image, "website" to website
            )

            navController!!.navigate(R.id.action_homeFragment_to_detailsFragment, bundel)
        })



        binding.btnGenerate.setOnClickListener {

            if (binding.edtUserName.text.toString().trim().isNotEmpty()) {

                binding.progressbar.visibility = View.VISIBLE

                viewModel.setUsername(binding.edtUserName.text.toString())


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


    private fun isOnline(): Boolean {
        val connectivityManager =
            activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
