package org.mercadolibre.test.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import org.mercadolibre.test.MainActivity
import org.mercadolibre.test.R
import org.mercadolibre.test.databinding.FragmentInformatonBinding
import org.mercadolibre.test.utils.extensions.setImageFromResourcesWithProgressBar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InformationFragment : Fragment() {

    companion object {
        fun newInstance(): InformationFragment = InformationFragment()
    }

    private lateinit var binding: FragmentInformatonBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentInformatonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindings()
    }

    private fun initBindings() {
        //hides activity's toolbar
        (activity as MainActivity).supportActionBar?.hide()
        with(binding.toolbar) {
            this.setTitle(R.string.title_info)
        }
        binding.generalInfoToolbarImage.setImageFromResourcesWithProgressBar(R.drawable.catalogo,
            binding.generalInfoProgress)
    }

    override fun onDestroy() {
        (activity as MainActivity).supportActionBar?.show()
        super.onDestroy()
    }
}