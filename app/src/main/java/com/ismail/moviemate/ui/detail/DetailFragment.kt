package com.ismail.moviemate.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ismail.core.data.source.Resource
import com.ismail.core.domain.model.Movie
import com.ismail.core.domain.ui.adapter.CasterAdapter
import com.ismail.core.domain.ui.adapter.MovieTrailerAdapter
import com.ismail.moviemate.R
import com.ismail.moviemate.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var detailMovie: Movie
    private var isFavorite by Delegates.notNull<Boolean>()
    private val movieTrailerAdapter: MovieTrailerAdapter by lazy {
        MovieTrailerAdapter{}
    }
    private val castertAdapter: CasterAdapter by lazy {
        CasterAdapter{}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(MOVIE_KEY)
        if (movie != null) {
            detailMovie = movie
        }
        binding.apply {
            Glide.with(requireContext()).load(movie?.img).into(ivMoviePoster)
            tvNameDetail.text = movie?.name
            tvAboutSinopsis.text = movie?.overview
            btnFav.setOnClickListener {
                it.findNavController().popBackStack()
            }
        }

        movie?.id?.let {
            viewModel.isFavorite(it).observe(viewLifecycleOwner) { fav ->
                binding.btnFav.apply {
                    if (fav == true) {
                        setImageResource(R.drawable.baseline_favorite_24)
                    } else {
                        setImageResource(R.drawable.baseline_favorite_border_24)
                    }
                    isFavorite = fav
                }
            }
            viewModel.getCastAndCrew(it)
            viewModel.getMovieTrailerById(it)
            fabFavoriteOnClick()
        }
        observeData()
        initRv()
        back()
    }

    private fun observeData() = binding.run {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.castResponse.collect {
                    when (it) {
                        is Resource.Success -> {
                            rvCast.visibility = View.VISIBLE
                            pbCast.visibility = View.GONE
                            it.data?.let { movies ->
                                castertAdapter.setItems(movies.shuffled())
                            }
                        }
                        is Resource.Loading -> {
                            rvCast.visibility = View.GONE
                            pbCast.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.movieTrailerResponse.collect{
                    when(it){
                        is Resource.Success ->{
                            rvMovieTrailer.visibility = View.VISIBLE
                            pbDetail.visibility = View.GONE
                            it.data?.let { trailer ->
                                movieTrailerAdapter.setItems(trailer.shuffled())
                            }
                        }
                        is Resource.Loading ->{
                            rvMovieTrailer.visibility = View.GONE
                            pbDetail.visibility = View.VISIBLE
                        }
                        is Resource.Error ->{}
                    }
                }
            }
        }
    }

    private fun back() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRv() {
        binding.rvMovieTrailer.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = movieTrailerAdapter
        }
        binding.rvCast.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = castertAdapter
        }
    }

    private fun fabFavoriteOnClick() {
        binding.btnFav.apply {
            setOnClickListener {
                isFavorite = if (!isFavorite) {
                    viewModel.insertMovie(detailMovie)
                    setImageResource(R.drawable.baseline_favorite_24)
                    true
                } else {
                    viewModel.deleteFavorite(detailMovie)
                    setImageResource(R.drawable.baseline_favorite_border_24)
                    true
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
    }
}