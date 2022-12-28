package task.doaaezzat.storeapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import task.doaaezzat.storeapp.R
import task.doaaezzat.storeapp.databinding.FragmentDetailsBinding


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // get product object from safe args and set it by data binding

        val product = DetailsFragmentArgs.fromBundle(
            requireArguments()
        ).selectedProduct

        binding.product = product
        return binding.root
    }

}