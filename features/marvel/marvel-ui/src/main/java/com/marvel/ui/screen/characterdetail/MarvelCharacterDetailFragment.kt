package com.marvel.ui.screen.characterdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.marvel.common_ui.di.ViewModelFactory
import com.marvel.ui.databinding.FragmentMarvelCharacterDetailBinding
import com.marvel.ui.di.MarvelComponentProvider
import com.marvel.ui.navigation.MarvelNavigator
import com.marvel.ui.screen.characterlist.MarvelCharacterListViewHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelCharacterDetailFragment : Fragment() {

    private val viewModel by viewModels<MarvelCharacterDetailViewModel> { viewModelFactory }

    @Inject internal lateinit var viewModelFactory: ViewModelFactory
    @Inject internal lateinit var viewHelper: MarvelCharacterListViewHelper

    private lateinit var binding: FragmentMarvelCharacterDetailBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as MarvelComponentProvider)
            .provideMarvelComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarvelCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachEventObservers()

        val characterUIModel = (activity as MarvelNavigator).provideCharacterDetailFragmentArgs(this)
        viewModel.setCharacter(characterUIModel = characterUIModel)
    }

    private fun attachEventObservers() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is MarvelCharacterDetailViewModel.Event.CharacterSetUp -> {
                        setCharacterToBinding()
                    }
                }
            }
        }
    }

    private fun setCharacterToBinding() {
        with(binding) {
            characterImage.load(viewModel.characterUIModel.thumbnail.thumbnailURL)
            name.text = viewModel.characterUIModel.name
            comics.text = viewModel.characterUIModel.comics.items.map { it.name }.joinToString(prefix = "Comics: ", separator = ", ")
            series.text = viewModel.characterUIModel.series.items.map { it.name }.joinToString(prefix = "Series: ", separator = ", ")
            stories.text = viewModel.characterUIModel.stories.items.map { it.name }.joinToString(prefix = "Stories: ", separator = ", ")
            events.text = viewModel.characterUIModel.events.items.map { it.name }.joinToString(prefix = "Events: ", separator = ", ")
        }
    }

}