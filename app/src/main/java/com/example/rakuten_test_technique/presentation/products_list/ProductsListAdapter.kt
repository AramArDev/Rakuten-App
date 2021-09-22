package com.example.rakuten_test_technique.presentation.products_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rakuten_test_technique.data.list_products_entity.Product
import com.example.rakuten_test_technique.databinding.ItemViewProductBinding

/**
 * The [ProductsListAdapter] class which is the RecyclerView adapter
 * which shows list of products.
 * Using [ListAdapter] and [DiffUtil.ItemCallback] for better performance.
 */
class ProductsListAdapter(private val clickListener: ProductClickListener) :
    ListAdapter<Product, ProductsListAdapter.ProductViewHolder>(ProductDiffCall()) {

    /**
     * The class [ProductDiffCall] which does not modify the whole list but
     * only the elements which have been modified added or deleted.
     */
    class ProductDiffCall : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * The [ProductViewHolder] class which contains item_view_product.xml.
     */
    class ProductViewHolder private constructor(private val binding: ItemViewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Make a binding between [product] and xmls product, and [clickListener].
         */
        fun bind(product: Product, clickListener: ProductClickListener) {
            binding.product = product
            binding.clickListener = clickListener
            // request to immediately execute all bindings
            binding.executePendingBindings()
        }

        companion object {

            /**
             * Creation of the [ProductViewHolder] from [ViewGroup].
             */
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewProductBinding.inflate(layoutInflater, parent, false)

                return ProductViewHolder(binding)
            }
        }
    }

    /**
     * The override method for creating [ProductViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    /**
     * The override method that binds [holder] and item to the position [position].
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, clickListener)
    }
}

/**
 * The [ProductClickListener] class which adds clickListener to the items.
 */
class ProductClickListener(val clickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) = clickListener(product)
}