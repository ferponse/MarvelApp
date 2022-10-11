package com.example.marvelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.databinding.ActivityMainBinding
import com.marvel.ui.model.CharacterUIModel
import com.marvel.ui.screen.characterlist.MarvelCharacterListFragmentDirections
import com.marvel.ui.navigation.MarvelNavigator
import com.marvel.ui.screen.characterdetail.MarvelCharacterDetailFragment
import com.marvel.ui.screen.characterdetail.MarvelCharacterDetailFragmentArgs

class MainActivity : AppCompatActivity(), MarvelNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun navigateToCharacterDetailFragment(navController: NavController, characterUIModel: CharacterUIModel) {
        navController.navigate(MarvelCharacterListFragmentDirections.actionMarvelCharacterListFragmentToMarvelCharacterDetailFragment(
            characterUIModel = characterUIModel
        ))
    }

    override fun provideCharacterDetailFragmentArgs(fragment: MarvelCharacterDetailFragment): CharacterUIModel {
        return fragment.navArgs<MarvelCharacterDetailFragmentArgs>().value.characterUIModel
    }

}
