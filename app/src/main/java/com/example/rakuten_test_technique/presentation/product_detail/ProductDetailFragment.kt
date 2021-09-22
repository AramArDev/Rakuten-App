package com.example.rakuten_test_technique.presentation.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.rakuten_test_technique.R
import com.example.rakuten_test_technique.databinding.FragmentProductDetailBinding
import com.example.rakuten_test_technique.utils.Constants.ERROR_MESSAGE
import com.example.rakuten_test_technique.utils.Constants.SIZE_LARGE
import com.example.rakuten_test_technique.utils.Constants.SIZE_NORMAL
import com.example.rakuten_test_technique.utils.Constants.ZERO
import dagger.hilt.android.AndroidEntryPoint

/**
 * The [ProductDetailFragment] which displays the screen of Product Detail.
 */
@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    // creation of ProductDetailViewModel which is attached to this fragment
    private val viewModel: ProductDetailViewModel by viewModels()

    // the slider adapter for product photos
    private lateinit var sliderAdapter: SliderAdapter

    // the binding between the fragment and the xml
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // adapter creation
        sliderAdapter = SliderAdapter()

        // creation and necessary bindings of variable binding
        binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.productImgs.adapter = sliderAdapter

        // recovery of the amount of slider dots
        viewModel.nbDots.observe(viewLifecycleOwner, {
            // dots creations
            createDots(it)
        })

        // recover the error and display a toast
        viewModel.error.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            }
        })

        // image cursor page change recovery
        binding.productImgs.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //change the preview of the selected dot
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        binding.shimmerViewContainer.startShimmer()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.observe(viewLifecycleOwner, {
            if(! it) {
//                viewModel.desactiveState()
                binding.shimmerViewContainer.hideShimmer()
            }
        })
    }


    /**
     * The method which adds [nbDots] dots to points container.
     */
    private fun createDots(nbDots: Int) {
        for (i in 0 until nbDots) {
            // dot creation
            val img = ImageView(requireContext())
            img.setImageResource(R.drawable.circle)
            // margin between dots creation
            val layoutParams = LinearLayout.LayoutParams(SIZE_LARGE, SIZE_LARGE)
            layoutParams.setMargins(SIZE_NORMAL, ZERO, SIZE_NORMAL, ZERO)
            // add the dot and margin to the dotsContainer
            binding.dotsContainer.addView(img, layoutParams)
        }
    }

    /**
     * Change the point preview at this [position].
     */
    private fun selectedDot(position: Int) {
        // get all ImageView dots
        binding.dotsContainer.forEachIndexed { index, view ->
            // change the preview by changing the resource
            if (index == position) {
                (view as ImageView).setImageResource(R.drawable.circle_selected)
            } else {
                (view as ImageView).setImageResource(R.drawable.circle)
            }
        }
        // request to immediately execute all bindings
        binding.executePendingBindings()
    }
}