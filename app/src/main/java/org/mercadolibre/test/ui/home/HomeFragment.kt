package org.mercadolibre.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.mercadolibre.test.MainActivity
import org.mercadolibre.test.R
import org.mercadolibre.test.data.api.RetrofitService
import org.mercadolibre.test.data.api.SearchService
import org.mercadolibre.test.databinding.FragmentHomeBinding
import org.mercadolibre.test.databinding.FragmentHomeBindingImpl
import org.mercadolibre.test.utils.list.InfiniteScrollListener


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    companion object {
        private val TAG = HomeFragment::class.java.name
        const val HOME_ARG = "HOME_ARG"
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding
    private var itemViewModel: DataViewModel? = null
    private var query: String = ""

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBindingImpl.inflate(inflater)
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val search = view.findViewById(R.id.search_button) as ImageButton
        search.setOnClickListener {
            val querySearch = view.findViewById(R.id.content_query) as EditText
            itemViewModel = null
            query = querySearch.text.toString()
            initBindings()
            initViewModels(query)
            initObservers()
        }
    }

    private fun initBindings() {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.homePhotosList.run {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    private fun initViewModels(query: String) {
        if (null == itemViewModel) {
            itemViewModel = ViewModelProvider(this@HomeFragment,
                ViewModelFactory(RetrofitService.createService(SearchService::class.java))).get(
                DataViewModel::class.java)
            itemViewModel?.loadData(query)
        }
    }

    private fun initObservers() {
        itemViewModel?.getItems()?.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is org.mercadolibre.test.utils.Result.Success -> {
                    renderList(result.data)
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    binding.homePhotosList.visibility = View.VISIBLE
                }
                is org.mercadolibre.test.utils.Result.InProgress -> {
                    binding.homePhotosProgressContainer.visibility = View.VISIBLE
                    binding.homePhotosList.visibility = View.GONE
                }
                is org.mercadolibre.test.utils.Result.Error -> {
                    binding.homePhotosProgressContainer.visibility = View.GONE
                    Toast.makeText(activity, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun fetchMoreData(query: String) {
        itemViewModel?.loadDataNextPage(query)
    }

    private fun renderList(photos: ArrayList<org.mercadolibre.test.data.model.Result>) {
        if (photos.isNotEmpty()) {
            //when screen starts
            if (itemViewModel?.getCurrentPage() == 1 || binding.homePhotosList.adapter?.itemCount == 0) {
                setRecyclerData(photos)
            } else { //when load more
                if (binding.homePhotosList.adapter == null) { //after load more
                    setRecyclerData(photos)
                    binding.homePhotosList.adapter?.notifyDataSetChanged()
                }
            }
            //load state of rv
            if (itemViewModel?.listState != null) {
                binding.homePhotosList.layoutManager?.onRestoreInstanceState(itemViewModel?.listState)
                itemViewModel?.listState = null
            }
        } else {
            showSnackBarMessage()
        }
    }

    private fun setRecyclerData(photos: ArrayList<org.mercadolibre.test.data.model.Result>) {
        with(binding.homePhotosList) {
            adapter = ItemsAdapter(photos)
            addOnScrollListener(
                InfiniteScrollListener({ fetchMoreData(query) },
                layoutManager as LinearLayoutManager
            )
            )
        }
    }

    private fun showSnackBarMessage() {
        val bottomNavView: BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
        Snackbar.make(bottomNavView, R.string.no_data_msg, Snackbar.LENGTH_SHORT).apply {
            anchorView = bottomNavView
        }.show()
    }

    override fun onResume() {
        (activity as MainActivity).supportActionBar?.show()
        super.onResume()
    }

    override fun onDestroyView() {
        itemViewModel?.listState = binding.homePhotosList.layoutManager?.onSaveInstanceState()
        super.onDestroyView()
    }
}