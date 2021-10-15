package com.harunkor.omdbbasicsample.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle


import android.view.LayoutInflater

import android.view.Gravity
import android.view.View

import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.harunkor.omdbbasicsample.R


class MovieDialog : Dialog {


    constructor(context: Context) : super(context) {
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        window!!.decorView.setBackgroundResource(android.R.color.transparent)

        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.progress_layout, null)

        val gifViewPlayer: GifMovieView  = view.findViewById(R.id.gifViewPlayer) as GifMovieView
        gifViewPlayer!!.setMovieResource(R.drawable.moviegif)


        setContentView(view)

    }
}