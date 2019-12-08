package github.com.githubuser.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import github.com.githubuser.R
import kotlinx.android.synthetic.main.fragment_network.*

/**
 * A simple [Fragment] subclass.
 */
class NetworkFragment : Fragment() {


    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_network, container, false)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btn_try.setOnClickListener {
            navController!!.navigate(R.id.action_networkFragment_to_homeFragment2)
        }


    }

}
