package com.marvel.ui.screen.characterlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.common_ui.di.ViewModelFactory
import com.marvel.common_ui.recyclerview.RecyclerOnScrollListener
import com.marvel.ui.adapter.MarvelCharacterListAdapter
import com.marvel.ui.adapter.item.MarvelCharacterHomeItem
import com.marvel.ui.databinding.FragmentMarvelCharacterListBinding
import com.marvel.ui.di.MarvelComponentProvider
import com.marvel.ui.model.CharacterUIModel
import com.marvel.ui.navigation.MarvelNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelCharacterListFragment : Fragment() {

    private val viewModel by viewModels<MarvelCharacterListViewModel> { viewModelFactory }

    @Inject internal lateinit var viewModelFactory: ViewModelFactory
    @Inject internal lateinit var viewHelper: MarvelCharacterListViewHelper

    private lateinit var binding: FragmentMarvelCharacterListBinding
    private lateinit var marvelNavigator: MarvelNavigator
    private lateinit var characterItemsAdapter: AsyncListDifferDelegationAdapter<MarvelCharacterHomeItem>
    private lateinit var recyclerViewCharactersOnScrollListener: RecyclerOnScrollListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as MarvelComponentProvider)
            .provideMarvelComponent()
            .inject(this)

        marvelNavigator = (activity as MarvelNavigator)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarvelCharacterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewCharacters()
        attachObservers()

        if (viewModel.charactersUIModel.isEmpty()) {
            viewModel.getMarvelCharactersFirstPage()
        } else {
            setCharacterAdapterItems(false)
        }
    }

    private fun setRecyclerViewCharacters() {
        characterItemsAdapter = MarvelCharacterListAdapter()
        val charactersLayoutManager = GridLayoutManager(context, 2)
        recyclerViewCharactersOnScrollListener = object : RecyclerOnScrollListener(charactersLayoutManager) {
            override fun onLoadMore() {
                viewModel.getMarvelCharactersNextPage()
            }
        }

        binding.characterRecyclerView.apply {
            layoutManager = charactersLayoutManager
            adapter = characterItemsAdapter
            addOnScrollListener(recyclerViewCharactersOnScrollListener)
        }
    }

    private fun attachObservers() {
        attachEventObservers()
        attachStateObservers()
    }

    private fun attachEventObservers() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is MarvelCharacterListViewModel.Event.GetCharactersFinishPaging -> {
                        binding.characterRecyclerView.clearOnScrollListeners()
                        setCharacterAdapterItems(loading = false)
                    }
                    is MarvelCharacterListViewModel.Event.GetCharactersContinuePaging -> {
                        recyclerViewCharactersOnScrollListener.loadOk()
                        setCharacterAdapterItems(loading = false)
                    }
                    is MarvelCharacterListViewModel.Event.Error.ApiError -> {
                        when(event.serviceErrorDomain) {
                            ServiceErrorDomain.BAD_REQUEST -> Toast.makeText(context, "Bad request error!", Toast.LENGTH_SHORT).show()
                            ServiceErrorDomain.INTERNAL_SERVER_ERROR -> Toast.makeText(context, "Internal server error!", Toast.LENGTH_SHORT).show()
                            ServiceErrorDomain.OTHER -> Toast.makeText(context, "Unknown error!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun attachStateObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MarvelCharacterListViewModel.State.IdleCharactersFirstPage -> {
                        binding.progressBar.isVisible = false
                        binding.characterRecyclerView.isVisible = true
                    }
                    is MarvelCharacterListViewModel.State.LoadingCharactersFirstPage -> {
                        binding.progressBar.isVisible = true
                        binding.characterRecyclerView.isVisible = false
                    }
                    is MarvelCharacterListViewModel.State.IdleNextCharactersPage -> {
                        setCharacterAdapterItems(loading = false)
                    }
                    is MarvelCharacterListViewModel.State.LoadingNextCharactersPage -> {
                        setCharacterAdapterItems(loading = true)
                    }
                }
            }
        }
    }

    private fun onCharacterClick(characterUIModel: CharacterUIModel) {
        marvelNavigator.navigateToCharacterDetailFragment(
            navController = findNavController(),
            characterUIModel = characterUIModel
        )
    }

    private fun setCharacterAdapterItems(loading: Boolean) {
        characterItemsAdapter.items = viewHelper.getCharacterAdapterItems(
            characterUIModel = viewModel.charactersUIModel,
            onCharacterClick = this@MarvelCharacterListFragment::onCharacterClick,
            loading = loading
        )
    }
}
