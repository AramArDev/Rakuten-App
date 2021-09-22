package com.example.rakuten_test_technique.utils

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.rakuten_test_technique.R
import com.example.rakuten_test_technique.data.list_products_entity.Product
import com.example.rakuten_test_technique.data.product_detail_entity.ProductDetail
import com.example.rakuten_test_technique.presentation.product_detail.SliderAdapter
import com.example.rakuten_test_technique.presentation.products_list.ProductsListAdapter
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

/**
 * Binding function which updates the list of products [data] to [recyclerView].
 */
@BindingAdapter("updateListProducts")
fun bindRecyclerViewUpdatesListProducts(recyclerView: RecyclerView, data: List<Product>?) {
    data?.let {
        // recovery adapter
        val adapter = recyclerView.adapter as ProductsListAdapter
        // add the list of products and notify the changes to the adapter
        adapter.submitList(it)
    }
}

/**
 * Binding function which updates the LARGE size [productDetail] image urls in the [viewPager] adapter.
 */
@SuppressLint("NotifyDataSetChanged")
@BindingAdapter("updateImages")
fun bindViewPagerUpdateImages(viewPager: ViewPager2, productDetail: ProductDetail?) {
    productDetail?.let {
        // recovery adapter
        val adapter = viewPager.adapter as SliderAdapter

        // add the urls of images in the urls list
        val urls = arrayListOf<String>()
        productDetail.images.forEach {
            urls.add(it.imagesUrls.entry[Constants.IMAGE_SIZE_URL].url)
        }

        //update the adapter list
        adapter.listUrls = urls
        adapter.notifyDataSetChanged()
    }
}

/**
 * Binding function which retrieves image from url [imgUrl].
 */
@BindingAdapter("imageFromUrl")
fun bindImageViewFromUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(it)
            .placeholder(R.drawable.rakuten)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

/**
 * Binding function which transforms a string into html.
 */
@BindingAdapter("htmlViewer")
fun bindTextViewHtmlViewer(textView: TextView, htmlString: String?) {
    htmlString?.let {
        textView.text = Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
    }
}

/**
 * Binding function which displays the best price of new or used product.
 *  - if the product is new, the text color will be black otherwise red.
 */
@BindingAdapter("newOrUsedBestPrice")
fun bindTextViewNewOrUsedBestPrice(textView: TextView, product: Product?) {
    product?.let {
        if (product.isNew) {
            val new = doubleWithoutZeros(product.newBestPrice)
            textView.text = Html.fromHtml(
                textView.context.getString(R.string.used_sale_format, new),
                Html.FROM_HTML_MODE_COMPACT
            )
            textView.setTextColor(textView.context.getColor(R.color.primaryTextColor))
        } else {
            val old = doubleWithoutZeros(product.usedBestPrice)
            textView.text = Html.fromHtml(
                textView.context.getString(R.string.new_sale_format, old),
                Html.FROM_HTML_MODE_COMPACT
            )
            textView.setTextColor(textView.context.getColor(R.color.primaryColor))
        }
    }
}

/**
 * Binding function which displays the price of new or used product
 *  - if the product is new, the text color will be red otherwise black
 */
@BindingAdapter(value = ["isNewPrice", "price"], requireAll = true)
fun bindTextViewNewOrUsedPrice(textView: TextView, isNew: Boolean?, price: Double?) {
    price?.let {
        isNew?.let {
            val new = doubleWithoutZeros(price)

//            textView.text = textView.context.getString(R.string.price_format, new)
            textView.text = Html.fromHtml(
                textView.context.getString(R.string.price_format, new),
                Html.FROM_HTML_MODE_COMPACT
            )
            textView.setTextAppearance(R.style.TextAppearance_Price)
            if (isNew) {
                textView.setTextColor(textView.context.getColor(R.color.primaryColor))
            } else {
                textView.setTextColor(textView.context.getColor(R.color.primaryTextColor))
            }
        }
    }
}

/**
 * Binding function which displays the quality of the used product,
 * if the product is new the TextView is not displayed
 */
@BindingAdapter(value = ["isNewQuality", "quality"], requireAll = true)
fun bindTextViewQuality(textView: TextView, isNew: Boolean?, quality: String?) {
    quality?.let {
        isNew?.let {
            if (isNew) {
                textView.visibility = View.GONE
            } else {
                textView.visibility = View.VISIBLE
                textView.text = quality
            }
        }
    }
}

/**
 * Function which removes zeros at the end of double and sends a string.
 */
private fun doubleWithoutZeros(number: Double): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(number)
}