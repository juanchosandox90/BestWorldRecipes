package com.sandoval.bestworldrecipes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.adapters.ViewPagerAdapter
import com.sandoval.bestworldrecipes.data.database.entity.FavoritesEntity
import com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments.IngredientsFragment
import com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments.InstructionsFragment
import com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments.OverviewFragment
import com.sandoval.bestworldrecipes.utils.Constants.Companion.RECIPE_RESULT_KEY
import com.sandoval.bestworldrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0

    private lateinit var saveMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val pagerAdapter = ViewPagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            saveRecipeToFavorites(item)
        } else if (item.itemId == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        saveMenuItem = menu!!.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(saveMenuItem)
        return true
    }

    private fun saveRecipeToFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(
            0,
            args.result
        )
        mainViewModel.insertFavoriteRecipes(favoritesEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar(applicationContext.getString(R.string.saved_favorite_recipe_snackbar_message))
        recipeSaved = true
    }

    private fun removeFromFavorites(menuItem: MenuItem) {
        val favoritesEntity = FavoritesEntity(
            savedRecipeId,
            args.result
        )
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(menuItem, R.color.white)
        showSnackBar(applicationContext.getString(R.string.remove_favorite_recipe_snackbar_message))
        recipeSaved = false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            detailsLayout,
            message,
            Snackbar.LENGTH_LONG
        ).setAction(applicationContext.getString(R.string.saved_favorite_recipe_snackbar_action)) {}
            .show()
    }

    private fun checkSavedRecipes(saveMenuItem: MenuItem) {
        mainViewModel.readFavoriteRecipes.observe(this, { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.id == args.result.id) {
                        changeMenuItemColor(saveMenuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.e("DetailsActivity: ", e.message.toString())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItemColor(saveMenuItem, R.color.white)
    }
}