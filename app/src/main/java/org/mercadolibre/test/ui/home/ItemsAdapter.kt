package org.mercadolibre.test.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.mercadolibre.test.R
import org.mercadolibre.test.data.model.DataDetails
import org.mercadolibre.test.data.model.Result
import org.mercadolibre.test.databinding.RowItemBinding
import org.mercadolibre.test.ui.details.DetailsFragment
import org.mercadolibre.test.utils.extensions.setImageFromUrlWithProgressBar


class ItemsAdapter(private val photosList: ArrayList<Result>) :
    RecyclerView.Adapter<ItemsAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(
            binding)
    }

    override fun getItemCount() = photosList.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    class PhotosViewHolder(rowBinding: RowItemBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding

        fun bind(photo: Result) {
            binding.itemBinding = photo
            binding.executePendingBindings()
            binding.rowPhotoRoverImg.setImageFromUrlWithProgressBar(photo.thumbnail!!, binding.rowPhotoRoverProgress)
            binding.root.setOnClickListener { view ->
                val data = DataDetails(photo.title!!,photo.price,photo.thumbnail!!)
                val bundle = bundleOf(DetailsFragment.PHOTO_ARG to data)
                view.findNavController().navigate(R.id.action_FirstFragment_to_detailsFragment, bundle)
            }
        }
    }


}