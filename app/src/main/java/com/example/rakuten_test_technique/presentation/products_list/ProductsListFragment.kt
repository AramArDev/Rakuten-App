package com.example.rakuten_test_technique.presentation.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.rakuten_test_technique.databinding.FragmentProductsListBinding
import com.example.rakuten_test_technique.presentation.State
import com.example.rakuten_test_technique.utils.Constants
import com.example.rakuten_test_technique.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * The [ProductsListFragment] which displays the screen of Products List.
 */
@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    // creation of ProductsListViewModel which is attached to this fragment
    private val viewModel: ProductsListViewModel by viewModels()

    // the binding between the fragment and the xml
    private lateinit var adapter: ProductsListAdapter

    // the slider adapter for product photos
    private lateinit var binding: FragmentProductsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // creation of the adapter by adding the click listener of the item
        adapter = ProductsListAdapter(ProductClickListener {
            viewModel.onProductClicked(it)
        })

        // creation and necessary bindings of variable binding
        binding = FragmentProductsListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.listProducts.adapter = adapter

        // add a separator between the items
        binding.listProducts.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        // recovery of the clicked product
        viewModel.productClicked.observe(viewLifecycleOwner, Observer {
            it?.let {
                // navigate to the ProductDetail page
                findNavController().navigate(
                    ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(
                        it.id
                    )
                )
                // productClicked reset
                viewModel.doneProductClicked()
            }
        })

        // keyword listener recovery
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // function is called when user clicked enter button
            override fun onQueryTextSubmit(query: String?): Boolean {
                // search the list of products using the keyword
                search(binding.searchView.query.toString())
                // hide keyboard
                hideKeyboard(requireContext(), requireView())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return binding.root
    }

    fun search(keyword: String) {
        // coroutine launch
        lifecycleScope.launch {
            // pass an empty list to the adapter for better performance
            adapter.submitList(emptyList())
            // state recovery
            viewModel.getListResponse(keyword)
                .collect {
                    when (it) {
                        // if product is loading
                        is State.LoadingState -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.listProducts.visibility = View.INVISIBLE
                        }
                        // if we could retrieve the list of products
                        is State.DataState -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            binding.listProducts.visibility = View.VISIBLE
                        }

                        // if an error happened
                        else -> {
                            Snackbar.make(
                                requireView(),
                                Constants.ERROR_MESSAGE,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
        }
    }
}