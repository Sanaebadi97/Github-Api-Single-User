package sanaebadi.info.montagithub.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation

import sanaebadi.info.montagithub.R
import sanaebadi.info.montagithub.databinding.FragmentNetworkBinding

/**
 * A simple [Fragment] subclass.
 */
class NetworkFragment : Fragment() {

    lateinit var binding: FragmentNetworkBinding

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_network, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.btnTry.setOnClickListener{
            navController!!.navigate(R.id.action_networkFragment_to_homeFragment2)
        }


    }

}
