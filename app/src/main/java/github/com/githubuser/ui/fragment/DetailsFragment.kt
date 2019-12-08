package github.com.githubuser.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import github.com.githubuser.R
import kotlinx.android.synthetic.main.fragment_details.*


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {


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
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val txtName = view.findViewById<AppCompatTextView>(R.id.txt_name)
        val txtBio = view.findViewById<AppCompatTextView>(R.id.txt_bio)
        val txtBlog = view.findViewById<AppCompatTextView>(R.id.txt_blog)
        val imgUser = view.findViewById<AppCompatImageView>(R.id.img_user)

        txtName.text = name
        txtBio.text = bio
        txtBlog.text = website

        Glide.with(this)
            .load(image)
            .apply(
                RequestOptions().optionalCircleCrop()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_image)
            )
            .into(imgUser)


        return view
    }


    //Navigate to home fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_test_another.setOnClickListener {
            navController!!.navigate(R.id.action_detailsFragment_to_homeFragment)
        }

    }


}
