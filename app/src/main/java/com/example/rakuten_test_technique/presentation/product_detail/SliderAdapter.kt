package com.example.rakuten_test_technique.presentation.product_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rakuten_test_technique.databinding.ItemViewSliderBinding

/**
 * The [SliderAdapter] class which is the RecyclerView adapter
 * which shows photos from a urls list [listUrls].
 */
class SliderAdapter(var listUrls: List<String> = listOf()) :
    RecyclerView.Adapter<SliderAdapter.ImageViewHolder>() {

    /**
     * The [ImageViewHolder] class which contains item_view_slider.xml.
     */
    class ImageViewHolder private constructor(private val binding: ItemViewSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Make a binding between [url] and xml url.
         */
        fun bind(url: String) {
            binding.url = url
            // request to immediately execute all bindings
            binding.executePendingBindings()
        }

        companion object {

            /**
             * Creation of the [ImageViewHolder] from [ViewGroup].
             */
            fun from(parent: ViewGroup): ImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewSliderBinding.inflate(layoutInflater, parent, false)

                return ImageViewHolder(binding)
            }
        }
    }

    /**
     * The override method for creating [ImageViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    /**
     * The override method that binds [holder] and item to the position [position].
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = listUrls[position]
        holder.bind(url)
    }

    /**
     * The override method which sends the size of the [listUrls].
     */
    override fun getItemCount(): Int = listUrls.size

}