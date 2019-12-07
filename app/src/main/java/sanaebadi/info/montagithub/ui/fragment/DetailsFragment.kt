package sanaebadi.info.montagithub.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sanaebadi.info.montagithub.R
import sanaebadi.info.montagithub.databinding.FragmentDetailsBinding


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private var name: String? = null
    private var bio: String? = null
    private var image: String? = null
    private var website: String? = null


    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments!!.getString("name")
        bio = arguments!!.getString("bio")
        image = arguments!!.getString("image")
        website = arguments!!.getString("website")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        binding.txtName.text = name
        binding.txtBio.text = bio
        binding.txtBlog.text = website

        Glide.with(this)
            .load(image)
            .apply(
                RequestOptions().optionalCircleCrop()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_image)
            )
            .into(binding.imgUser)


        return binding.root
    }


    //Navigate to home fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnTestAnother.setOnClickListener{
            navController!!.navigate(R.id.action_detailsFragment_to_homeFragment)
        }

    }


}
