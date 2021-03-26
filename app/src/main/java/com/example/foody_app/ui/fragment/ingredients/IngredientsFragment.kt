package com.example.foody_app.ui.fragment.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody_app.R
import com.example.foody_app.Util.Constants.Companion.RECIPE_RESULT_KEY
import com.example.foody_app.adapters.IngredientsAdapter
import com.example.foody_app.model.Result
import kotlinx.android.synthetic.main.fragment_ingredients.view.*

class IngredientsFragment : Fragment() {
    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)
        setupRecyclerView(view)
        myBundle?.extendedIngredients?.let {
            mAdapter.setData(it)
        }
        return view
    }

    private fun setupRecyclerView(view: View) {
        view.ingredients_recyclerview.adapter = mAdapter
        view.ingredients_recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }
}