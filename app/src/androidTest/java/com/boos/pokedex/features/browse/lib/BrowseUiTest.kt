package com.boos.pokedex.features.browse.lib

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.boos.pokedex.features.browse.BrowseUi
import com.boos.pokedex.features.browse.BrowseUiState
import com.boos.pokedex.theme.PokedexTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseUiTest {

    @get:Rule
    val composeRule = createComposeRule()


    private var onSearchInvoked: Int = 0
    private val onSearchUpdated: (String) -> Unit = { onSearchInvoked += 1 }

    private var onPokemonClickInvoked: Int = 0
    private val onPokemonClick: () -> Unit = { onPokemonClickInvoked += 1 }

    private var onRefreshSwipeInvoked: Int = 0
    private val onRefreshSwipe: () -> Unit = { onRefreshSwipeInvoked += 1 }

    private val defaultUiState = BrowseUiState()

    @Before
    fun setup() {
        clearInvokes()
    }

    private fun clearInvokes() {
        onSearchInvoked = 0
        onPokemonClickInvoked = 0
        onRefreshSwipeInvoked = 0
    }

    @Test
    fun browseUi() {
        composeRule.setContent {
            PokedexTheme {
                BrowseUi(
                    uiState = defaultUiState,
                    onSearchUpdated = onSearchUpdated,
                    onPokemonClick = onPokemonClick,
                    onRefreshSwipe = onRefreshSwipe
                )
            }
        }
        Thread.sleep(10000)
    }

}