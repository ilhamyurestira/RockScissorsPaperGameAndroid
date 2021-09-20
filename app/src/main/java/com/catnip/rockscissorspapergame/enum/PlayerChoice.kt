package com.catnip.rockscissorspapergame.enum

enum class PlayerChoice(val index: Int) {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);
    companion object {
        fun getValueFromIndex(index: Int) = values()[index]
    }
}