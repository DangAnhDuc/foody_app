package com.example.foody_app.ui.fragment.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody_app.R
import com.example.foody_app.adapters.FavoriteRecipeAdapter
import com.example.foody_app.databinding.FragmentFavoriteRecipesBinding
import com.example.foody_app.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_recipes.*
import kotlinx.android.synthetic.main.fragment_favorite_recipes.view.*

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: FavoriteRecipeAdapter by lazy { FavoriteRecipeAdapter(requireActivity(),mainViewModel) }
    private var _binding: FragmentFavoriteRecipesBinding?=null
    private val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFavoriteRecipesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner= this
        binding.mainViewModel
        setHasOptionsMenu(true)
        setupRecyclerView(binding.favoriteRecipesRecyclerView)
        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner, {
                favoritesEntity ->
            mAdapter.setData(favoritesEntity)
            if(favoritesEntity.isNullOrEmpty()){
                no_data_imageView.visibility=View.VISIBLE
                no_data_textView.visibility= View.VISIBLE
                favoriteRecipesRecyclerView.visibility= View.INVISIBLE
            } else {
                no_data_imageView.visibility=View.INVISIBLE
                no_data_textView.visibility= View.INVISIBLE
                favoriteRecipesRecyclerView.visibility= View.VISIBLE
            }
        })
        return binding.root
    }

    private fun setupRecyclerView(view: View) {
        view.favoriteRecipesRecyclerView.adapter = mAdapter
        view.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.deleteAll_favorite_recipes_menu){
            mainViewModel.deleteAllFavoriteRecipes()
            showSnackbar("All recipes removed")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSnackbar(message: String){
        Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).setAction("Okay"){}
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
        mAdapter.clearContextualActionMode()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu,menu)

    }
}