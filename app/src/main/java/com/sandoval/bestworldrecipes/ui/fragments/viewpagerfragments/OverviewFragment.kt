package com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.utils.Constants.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_overview.view.*
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments

        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        mView.recipeImgView.load(myBundle?.image)
        mView.recipeDetailTitleText.text = myBundle?.title
        mView.likesText.text = myBundle?.aggregateLikes.toString()
        mView.minutesText.text = myBundle?.readyInMinutes.toString()

        /** Fix the HTML tags not parsed that came inside the Api Spoonacular
        mView.summaryText.text = myBundle?.summary **/

        myBundle?.summary.let {
            val summaryParsed = Jsoup.parse(it).text()
            mView.summaryText.text = summaryParsed
        }

        if (myBundle?.vegetarian == true) {
            mView.vegetarianImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.vegetarianText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.vegan == true) {
            mView.veganImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.veganText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.glutenFree == true) {
            mView.glutenFreeImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.glutenFreeText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.dairyFree == true) {
            mView.dairyFreeImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.dairyFreeText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.veryHealthy == true) {
            mView.healthyImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.healthyText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        if (myBundle?.cheap == true) {
            mView.cheapImgView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            mView.cheapText.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }

        return mView
    }
}