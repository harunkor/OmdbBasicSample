package com.harunkor.omdbbasicsample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.harunkor.omdbbasicsample.R
import com.harunkor.omdbbasicsample.model.Detail.Detail
import com.harunkor.omdbbasicsample.utils.Constants
import com.harunkor.omdbbasicsample.utils.MovieDialog
import com.harunkor.omdbbasicsample.utils.RetrofitDetailCallBack
import com.harunkor.omdbbasicsample.viewmodel.detail.DetailViewModel
import com.harunkor.omdbbasicsample.viewmodel.search.SearchViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation


private const val ARG_imdbID = "imdbID"

class DetailFragment : Fragment(), View.OnClickListener {


    private lateinit var imdbID: String
    private lateinit var backButton:ImageButton
    private lateinit var playButton:View
    private lateinit var imdbRating:TextView
    private lateinit var title:TextView
    private lateinit var genre:TextView
    private lateinit var imdbVotes:TextView
    private lateinit var runtime:TextView
    private lateinit var plot:TextView
    private lateinit var actors:TextView
    private lateinit var director:TextView
    private lateinit var writer:TextView
    private lateinit var country:TextView
    private lateinit var awards:TextView
    private lateinit var released:TextView
    private lateinit var topImageView:ImageView
    private lateinit var posterLeft:ImageView
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var movieDialog: MovieDialog
    private lateinit var detaildata:Detail;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDialog=MovieDialog(context!!)
        movieDialog.show()
        arguments?.let {
            imdbID = it.getString(ARG_imdbID)!!

        }
        setViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View=inflater.inflate(R.layout.fragment_detail, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View){
        backButton=view.findViewById(R.id.backbtn)
        playButton=view.findViewById(R.id.btnplay)
        backButton.setOnClickListener(this)
        playButton.setOnClickListener(this)
        imdbRating=view.findViewById(R.id.fimdbrating)
        title=view.findViewById(R.id.ftitle)
        genre=view.findViewById(R.id.fgenre)
        imdbVotes=view.findViewById(R.id.fimdbvotes)
        runtime=view.findViewById(R.id.fruntime)
        plot=view.findViewById(R.id.fplot)
        actors=view.findViewById(R.id.factors)
        director=view.findViewById(R.id.fdirector)
        writer=view.findViewById(R.id.fwriter)
        country=view.findViewById(R.id.fcountry)
        awards=view.findViewById(R.id.fawards)
        released=view.findViewById(R.id.freleased)
        topImageView=view.findViewById(R.id.top_img)
        posterLeft=view.findViewById(R.id.filmimgleft)
    }
    fun setData(detail: Detail){
        imdbRating.text=detail.imdbRating.toString()
        title.text=detail.Title
        genre.text=detail.Genre
        imdbVotes.text=detail.imdbVotes+getString(R.string.imdbvoted)
        runtime.text=detail.Runtime
        plot.text=detail.Plot
        actors.text=getString(R.string.actors)+detail.Actors
        director.text=getString(R.string.director)+detail.Director
        writer.text=getString(R.string.writer)+detail.Writer
        country.text=getString(R.string.country)+detail.Country
        awards.text=getString(R.string.awards)+detail.Awards
        released.text=getString(R.string.released)+detail.Released
        Picasso.get().load(detail.Poster).transform(BlurTransformation(context, 25, 1)).into(topImageView);
        Picasso.get().load(detail.Poster).into(posterLeft);
        movieDialog.dismiss()
    }

    private fun setViewModel(){
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.int(imdbID,Constants.PLOT,object :RetrofitDetailCallBack{
            override fun onSucess(detail: Detail) {
                setData(detail)
            }

            override fun onFailure() {
                movieDialog.dismiss()
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.backbtn ->{
                requireActivity().onBackPressed()
            }
            R.id.btnplay ->{
                Toast.makeText(context,getString(R.string.comingsoon),Toast.LENGTH_LONG).show()
            }
        }

    }

}