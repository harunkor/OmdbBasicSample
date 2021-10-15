package com.harunkor.omdbbasicsample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.omdbbasicsample.R
import com.harunkor.omdbbasicsample.adapter.SearchAdapter
import com.harunkor.omdbbasicsample.model.Search.SearchResponse
import com.harunkor.omdbbasicsample.utils.MovieDialog
import com.harunkor.omdbbasicsample.utils.RetrofitSearchCallBack
import com.harunkor.omdbbasicsample.viewmodel.search.SearchViewModel


class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var searchViewModel: SearchViewModel;
    private lateinit var searView:SearchView
    private lateinit var movieList:RecyclerView
    private lateinit var mainImg:ImageView
    private lateinit var navController: NavController
    private lateinit var adapter: SearchAdapter
    private lateinit var movieDialog: MovieDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_search, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View){
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searView=view.findViewById(R.id.searchView)
        movieList=view.findViewById(R.id.listmovies)
        mainImg=view.findViewById(R.id.mainimg)
        searView.setOnQueryTextListener(this)
        navController= NavHostFragment.findNavController(this)
        movieList.setHasFixedSize(true);
        movieList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        movieDialog=MovieDialog(context!!)


    }


    override fun onQueryTextSubmit(s: String): Boolean {
        movieDialog.show()
        search(s)
        searView.clearFocus();
        return true
    }

    private fun search(s:String){
        searchViewModel.init(s!!,"1",object :RetrofitSearchCallBack{
            override fun onSucess(searchResponse:SearchResponse) {
                if(searchResponse.Search!=null){
                    adapter=SearchAdapter(searchResponse.Search,navController)
                    movieList.adapter=adapter
                    hideMainLogo()
                }else{
                    Toast.makeText(requireContext(),getString(R.string.notfound),Toast.LENGTH_LONG).show()
                }
                movieDialog.dismiss()
            }
            override fun onFailure() {
                movieDialog.dismiss()
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
    override fun onResume() {
        super.onResume()
        if(!searView.query.isNullOrEmpty()){
            movieDialog.show()
            search(searView.query.toString())
        }else{
            visibleMainLogo()
        }
    }

    private fun hideMainLogo(){
        mainImg.isVisible=false

    }

    private fun visibleMainLogo(){
        mainImg.isVisible=true

    }

}