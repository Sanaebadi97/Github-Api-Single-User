package github.com.githubuser.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import github.com.githubuser.R
import github.com.githubuser.viewModel.DetailsMainModel
import github.com.githubuser.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.progressbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {


    private var name: String? = null
    private var bio: String? = null
    private var image: String? = null
    private var website: String? = null
    private var login: String? = null

    private val viewModel: DetailsMainModel by viewModel()


    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments!!.getString("name")
        bio = arguments!!.getString("bio")
        image = arguments!!.getString("image")
        website = arguments!!.getString("website")
        website = arguments!!.getString("website")
        login = arguments!!.getString("login")
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

        if (name != null && bio != null && image != null && website != null) {
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

        } else {
            Toast.makeText(
                activity,
                getString(R.string.user_not_valid),
                Toast.LENGTH_SHORT
            )
                .show()

        }

        return view
    }


    //Navigate to home fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btn_test_another.setOnClickListener {
            navController!!.navigate(R.id.action_detailsFragment_to_homeFragment)
        }

        //observe view model

        viewModel.repos.observe(viewLifecycleOwner, Observer { repos ->
            println("REPOS $repos")

            progressbar.visibility = View.GONE

            progressbar.visibility = View.VISIBLE

        })

        btn_repos_list.setOnClickListener {
            viewModel.setUsername(login!!)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }

}
