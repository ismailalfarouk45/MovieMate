package com.ismail.favorite.ui.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismail.core.domain.ui.adapter.FavoriteAdapter
import com.ismail.favorite.di.DaggerFavoriteComponent
import com.ismail.favorite.databinding.FragmentFavoriteBinding
import com.ismail.moviemate.di.FavoriteModule
import com.ismail.moviemate.ui.detail.DetailFragment
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter { movie ->
            findNavController().navigate(
                com.ismail.moviemate.R.id.action_favoriteFragment_to_detailFragment,
                Bundle().apply {
                    putParcelable(DetailFragment.MOVIE_KEY, movie)
                }
            )
        }
    }
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(), FavoriteModule::class.java
                )
            ).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back()
        observeFav()
        initRv()
    }

    private fun initRv() {
        binding.rvFav.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun observeFav() {
        viewModel.getAllFavorite.observe(viewLifecycleOwner) {
            favoriteAdapter.setItems(it)
            if (it.isNotEmpty()) {
                binding.rvFav.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            }else{
                binding.rvFav.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun back() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}