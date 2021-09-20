package com.catnip.rockscissorspapergame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.catnip.rockscissorspapergame.R
import com.catnip.rockscissorspapergame.databinding.ActivityMainBinding
import com.catnip.rockscissorspapergame.enum.Computer
import com.catnip.rockscissorspapergame.enum.PlayerChoice
import com.catnip.rockscissorspapergame.enum.User
import com.catnip.rockscissorspapergame.interfaces.PlayGame

class MainActivity : AppCompatActivity(), PlayGame {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var computer: Computer
    private lateinit var user: User
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        computer = Computer()
        user = User()

        startGame()
    }

    fun startGame() {
        initialHero()
        setHeroAssets()
        initialState()
        setOnClickListeners()
    }

    private fun initialHero() {
        user.heroes = arrayListOf(
            binding.ivRockPlayer,
            binding.ivPaperPlayer,
            binding.ivScissorsPlayer
        )

        computer.heroes = arrayListOf(
            binding.ivRockCom,
            binding.ivPaperCom,
            binding.ivScissorsCom
        )
    }

    private fun setHeroAssets() {
        user.heroes[PlayerChoice.ROCK.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_rock
            )
        )
        user.heroes[PlayerChoice.PAPER.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_paper
            )
        )
        user.heroes[PlayerChoice.SCISSORS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_scissors
            )
        )

        computer.heroes[PlayerChoice.ROCK.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_rock
            )
        )
        computer.heroes[PlayerChoice.PAPER.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_paper
            )
        )
        computer.heroes[PlayerChoice.SCISSORS.index].setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_scissors
            )
        )

    }

    private fun initialState() {
        (user.heroes + computer.heroes).forEach { it.background = null }
        binding.ivVersus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_vs))
        isGameFinished = false
    }

    private fun setOnClickListeners() {
        user.heroes.forEachIndexed { index, hero ->
            hero.setOnClickListener {
                if (!isGameFinished) {
                    Log.i(TAG, "setOnClickListeners: ${PlayerChoice.getValueFromIndex(index)}")
                    user.choice = index
                    it.background = ContextCompat.getDrawable(this, R.drawable.img_select)
                    computer.choice = (0..2).random()
                    computer.heroes[computer.choice].background =
                        ContextCompat.getDrawable(this, R.drawable.img_select)
                    decideWinner()
                    isGameFinished = true
                }
            }
        }

        binding.refresh.setOnClickListener {
            initialState()
        }
    }

    override fun decideWinner() {
        when {
            (user.choice + 1) % 3 == computer.choice -> {
                Log.i(TAG, "decideWinner: Player 2 Win")
                binding.ivVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_lose
                    )
                )
            }
            user.choice == computer.choice -> {
                Log.i(TAG, "decideWinner: Draw")
                binding.ivVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_draw
                    )
                )
            }
            else -> {
                Log.i(TAG, "decideWinner: Player 1 Win")
                binding.ivVersus.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_win
                    )
                )
            }
        }
    }
}