package task.doaaezzat.storeapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import task.doaaezzat.storeapp.model.Result
import task.doaaezzat.storeapp.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel   by lazy { ViewModelProvider(
        requireActivity()).get(MainViewModel::class.java)}

    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentMainBinding.inflate(inflater)
         binding.lifecycleOwner = this

// creating adapter and set it to recyclyr view
        val adapter = ProductAdapter(ProductAdapter.ProductClickListener{
                product ->  viewModel.onProductClicked(product)
        })
        binding.recyclerView.adapter = adapter

  // just observe product list and set data depend on status
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                        adapter.submitList(it.data)
                    binding.loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    it.message?.let {
                        showError(it)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }

        })
// navigate to details fragment with arguments to send product object , by observe value of navigate to product details
        viewModel.navigateToProductDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(
                        it
                    )
                )
                viewModel.onProductClickedNavigated()
            }
        })

        return binding.root
    }

    private fun showError(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

}