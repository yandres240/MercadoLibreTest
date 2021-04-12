package org.mercadolibre.test.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.mercadolibre.test.MainActivity
import org.mercadolibre.test.data.model.DataDetails
import org.mercadolibre.test.data.model.Result
import org.mercadolibre.test.databinding.FragmentDetailsBinding
import org.mercadolibre.test.databinding.FragmentDetailsBindingImpl
import org.mercadolibre.test.utils.extensions.setImageFromUrl
import org.mercadolibre.test.utils.extensions.setImageFromUrlWithProgressBar
import org.mercadolibre.test.utils.extensions.zoomImageFromThumb

class DetailsFragment : Fragment() {

    companion object {
        private val TAG = DetailsFragment::class.java.name
        const val PHOTO_ARG = "PHOTO_ARG"
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var photo: DataDetails
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArguments()
    }

    private fun loadArguments() {
        arguments?.getParcelable<DataDetails>(PHOTO_ARG)?.let {
            photo = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        
        binding = FragmentDetailsBindingImpl.inflate(inflater)
        binding.photoBinding = photo
        binding.lifecycleOwner = this@DetailsFragment
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        binding.photoDetailsImageZoom.setImageFromUrl(photo.thumbnail!!)
        with(binding.photoDetailsImage) {
            setImageFromUrlWithProgressBar(photo.thumbnail!!, binding.photoDetailsProgress)
            setOnClickListener {
                zoomImageFromThumb(binding.photoDetailsImageZoom,
                    binding.photoDetailsContainer,
                    shortAnimationDuration)
            }
        }
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }
}
