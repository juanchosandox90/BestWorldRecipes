package com.sandoval.bestworldrecipes.ui.fragments.viewpagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.sandoval.bestworldrecipes.R
import com.sandoval.bestworldrecipes.data.models.Result
import com.sandoval.bestworldrecipes.utils.Constants
import kotlinx.android.synthetic.main.fragment_instructions.view.*

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_instructions, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        mView.instructionsWebview.webViewClient = object : WebViewClient() {

        }

        myBundle?.sourceUrl?.let { mView.instructionsWebview.loadUrl(it) }

        return mView
    }

}