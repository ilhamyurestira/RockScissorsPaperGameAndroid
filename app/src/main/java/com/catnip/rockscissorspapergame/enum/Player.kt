package com.catnip.rockscissorspapergame.enum

import android.widget.ImageView

abstract class Player {
    lateinit var name: String
    var choice: Int = -1
    lateinit var heroes: ArrayList<ImageView>
}