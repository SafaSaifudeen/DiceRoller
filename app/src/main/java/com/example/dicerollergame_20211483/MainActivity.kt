package com.example.dicerollergame_20211483



import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*


class MainActivity : AppCompatActivity() {
    var iv_cpu: ImageView? = null
    var iv_cpu2: ImageView? = null
    var iv_cpu3: ImageView? = null
    var iv_cpu4: ImageView? = null
    var iv_cpu5: ImageView? = null

    var iv_player: ImageView? = null
    var iv_player2: ImageView? = null
    var iv_player3: ImageView? = null
    var iv_player4: ImageView? = null
    var iv_player5: ImageView? = null

     var rollsRemaining = 0


     private var tv_cpu: TextView? = null
    private var tv_player: TextView? = null

    lateinit var targetScoreTextView: TextView
    var cpuPoints = 0
    var playerPoints = 0

    var targetScore=101
    var gametype="easy"

    var humanWins=0
    var computerWins=0



    var r: Random? = null
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gameMode: Button = findViewById(R.id.gamemode)



        iv_cpu = findViewById<View>(R.id.iv_cpu) as ImageView
        iv_cpu2 = findViewById<View>(R.id.iv_cpu2) as ImageView
        iv_cpu3 = findViewById<View>(R.id.iv_cpu3) as ImageView
        iv_cpu4 = findViewById<View>(R.id.iv_cpu4) as ImageView
        iv_cpu5 = findViewById<View>(R.id.iv_cpu5) as ImageView



        iv_player = findViewById<View>(R.id.iv_player) as ImageView
        iv_player2 = findViewById<View>(R.id.iv_player2) as ImageView
        iv_player3= findViewById<View>(R.id.iv_player3) as ImageView
        iv_player4 = findViewById<View>(R.id.iv_player4) as ImageView
        iv_player5 = findViewById<View>(R.id.iv_player5) as ImageView
        tv_cpu = findViewById<View>(R.id.tv_cpu) as TextView
        tv_player = findViewById<View>(R.id.tv_player) as TextView
        r = Random()
        targetScoreTextView = findViewById(R.id.targetScore_tv)
        var roll = findViewById<Button>(R.id.button)
        var score = findViewById<Button>(R.id.button2)
        var rethrow = findViewById<Button>(R.id.rethrow)

        //generating random numbers
        var cpuThrow = r!!.nextInt(6) + 1
        var cpuThrow2 = r!!.nextInt(6) + 1
        var cpuThrow3 = r!!.nextInt(6) + 1
        var cpuThrow4 = r!!.nextInt(6) + 1
        var cpuThrow5 = r!!.nextInt(6) + 1

        var playerThrow = r!!.nextInt(6) + 1
        var playerThrow2 = r!!.nextInt(6) + 1
        var playerThrow3 = r!!.nextInt(6) + 1
        var playerThrow4 = r!!.nextInt(6) + 1
        var playerThrow5 = r!!.nextInt(6) + 1

        setTargetScoreDialog()
        loadWinCount()
        updateWinCount()



        roll.setOnClickListener {
            r = Random()
            cpuThrow = r!!.nextInt(6) + 1
            cpuThrow2 = r!!.nextInt(6) + 1
            cpuThrow3 = r!!.nextInt(6) + 1
            cpuThrow4 = r!!.nextInt(6) + 1
            cpuThrow5 = r!!.nextInt(6) + 1

            playerThrow = r!!.nextInt(6) + 1
            playerThrow2 = r!!.nextInt(6) + 1
            playerThrow3 = r!!.nextInt(6) + 1
            playerThrow4 = r!!.nextInt(6) + 1
            playerThrow5 = r!!.nextInt(6) + 1
            setImageCPU(cpuThrow)
            setImageCPU2(cpuThrow2)
            setImageCPU3(cpuThrow3)
            setImageCPU4(cpuThrow4)
            setImageCPU5(cpuThrow5)
            setImagePlayer(playerThrow)
            setImagePlayer2(playerThrow2)
            setImagePlayer3(playerThrow3)
            setImagePlayer4(playerThrow4)
            setImagePlayer5(playerThrow5)
            //updating the score

            cpuPoints =cpuPoints+cpuThrow+cpuThrow2+cpuThrow3+cpuThrow4+cpuThrow5
            playerPoints= playerPoints + playerThrow+playerThrow2+playerThrow3+playerThrow4+playerThrow5


            tv_cpu!!.text = "CPU: $cpuPoints"
            tv_player!!.text = "YOU: $playerPoints"
            updateScoreboard()
            showWinner()
            //adding animation to rotate
            val rotate = AnimationUtils.loadAnimation(
                applicationContext, R.anim.rotate
            )
            //https://youtu.be/cQl2cRYggGs


            iv_cpu!!.startAnimation(rotate)
            iv_cpu2!!.startAnimation(rotate)
            iv_cpu3!!.startAnimation(rotate)
            iv_cpu4!!.startAnimation(rotate)
            iv_cpu5!!.startAnimation(rotate)
            iv_player!!.startAnimation(rotate)
            iv_player2!!.startAnimation(rotate)
            iv_player3!!.startAnimation(rotate)
            iv_player4!!.startAnimation(rotate)
            iv_player5!!.startAnimation(rotate)

            }
        //change the game mode

        gameMode.setOnClickListener{
            if (gametype == "easy" ){
                gameMode.setText("hard")
                gametype="hard"
         }
            else{
                gameMode.setText("easy")
                gametype="easy"
            }
         }


        score.setOnClickListener {

            var randomreroll = 1
            var randomreroll2 = 1
            var randomreroll3 = 1
            var randomreroll4 = 1
            var randomreroll5 = 1

            randomreroll = (1..6).random()
            randomreroll2 = (1..6).random()
            randomreroll3 = (1..6).random()
            randomreroll4 = (1..6).random()
            randomreroll5 = (1..6).random()
            // computer optimized stratergy; when the user sets the game mode to hard then the optimized stratergy is appleied where the cpu rolls the dices that are less than 3



            for (i in 1..2) {
                if (gametype=="hard"){
                    if (cpuThrow < 3) {
                    iv_cpu!!.setImageResource(getDiceImageResource(randomreroll))
                    cpuThrow = randomreroll
                }
                    if (cpuThrow2 < 3) {
                    iv_cpu2!!.setImageResource(getDiceImageResource(randomreroll2))
                    cpuThrow2 = randomreroll2
                }
                    if (cpuThrow3 < 3) {
                    iv_cpu3!!.setImageResource(getDiceImageResource(randomreroll3))
                    cpuThrow3 = randomreroll3
                }
                    if (cpuThrow4 < 3) {
                    iv_cpu4!!.setImageResource(getDiceImageResource(randomreroll4))
                    cpuThrow4 = randomreroll4
                }
                    if (cpuThrow5 < 3) {
                    iv_cpu5!!.setImageResource(getDiceImageResource(randomreroll5))
                    cpuThrow5 = randomreroll5
                }
                }

                else if (gametype== "easy") {
                    var ran= Random()
                    var cpukeep= ran.nextBoolean()
                    var cpukeep2= ran.nextBoolean()
                    var cpukeep3= ran.nextBoolean()
                    var cpukeep4= ran.nextBoolean()
                    var cpukeep5= ran.nextBoolean()
                    if (cpukeep) {
                        iv_cpu!!.setImageResource(getDiceImageResource(randomreroll))
                        cpuThrow = randomreroll
                    }
                    if (cpukeep2) {
                        iv_cpu2!!.setImageResource(getDiceImageResource(randomreroll2))
                        cpuThrow2 = randomreroll2
                    }
                    if (cpukeep3) {
                        iv_cpu3!!.setImageResource(getDiceImageResource(randomreroll3))
                        cpuThrow3 = randomreroll3
                    }
                    if (cpukeep4) {
                        iv_cpu4!!.setImageResource(getDiceImageResource(randomreroll4))
                        cpuThrow4 = randomreroll4
                    }
                    if (cpukeep5) {
                        iv_cpu5!!.setImageResource(getDiceImageResource(randomreroll5))
                        cpuThrow5 = randomreroll5
                    }
                }


            }

            val alertMessage = AlertDialog.Builder(this)
            alertMessage.setTitle("About")
            alertMessage.setMessage("Computer will be using its reroll ")
            alertMessage.setPositiveButton("ok") { dialog, which -> dialog.dismiss() }
            alertMessage.show()
            cpuPoints = cpuPoints + cpuThrow + cpuThrow2 + cpuThrow3 + cpuThrow4 + cpuThrow5
            playerPoints = playerPoints + playerThrow + playerThrow2 + playerThrow3 + playerThrow4 + playerThrow5
            tv_cpu!!.text = "CPU: $cpuPoints"
            tv_player!!.text = "YOU: $playerPoints"
            updateScoreboard()

        }
        var dice1Value = 1
        var dice2Value = 1
        var dice3Value = 1
        var dice4Value = 1
        var dice5Value = 1

        //rethrow button for the user to use its rethrow
        rethrow.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_reroll, null)
            dialog.setView(view)
            //check box is used to select the dices that are neede to keep from rerolling

            val dice1CheckBox = view.findViewById<CheckBox>(R.id.dice1_checkbox)
            val dice2CheckBox = view.findViewById<CheckBox>(R.id.dice2_checkbox)
            val dice3CheckBox = view.findViewById<CheckBox>(R.id.dice3_checkbox)
            val dice4CheckBox = view.findViewById<CheckBox>(R.id.dice4_checkbox)
            val dice5CheckBox = view.findViewById<CheckBox>(R.id.dice5_checkbox)


            dialog.setPositiveButton("Reroll") { _, _ ->
                //enables the rethrow for only twice
                if (rollsRemaining <2) {
                    rollsRemaining++



                    if (!dice1CheckBox.isChecked) {
                        dice1Value = (1..6).random()
                        iv_player!!.setImageResource(getDiceImageResource(dice1Value))
                        playerThrow=dice1Value
                    }
                    if (!dice2CheckBox.isChecked) {
                        dice2Value = (1..6).random()
                        iv_player2!!.setImageResource(getDiceImageResource(dice2Value))
                        playerThrow2=dice2Value
                    }
                    if (!dice3CheckBox.isChecked) {
                        dice3Value = (1..6).random()
                        iv_player3!!.setImageResource(getDiceImageResource(dice3Value))
                        playerThrow3=dice3Value
                    }
                    if (!dice4CheckBox.isChecked) {
                        dice4Value = (1..6).random()
                        iv_player4!!.setImageResource(getDiceImageResource(dice4Value))
                        playerThrow4=dice4Value
                    }
                    if (!dice5CheckBox.isChecked) {
                        dice5Value = (1..6).random()
                        iv_player5!!.setImageResource(getDiceImageResource(dice5Value))
                        playerThrow5=dice5Value
                    }

                }
                if (rollsRemaining==2) {
                    Toast.makeText(this, "You have no more rolls remaining", Toast.LENGTH_SHORT).show()
                    rethrow.isEnabled = false

                }
            }

            dialog.setNegativeButton("Cancel") { _, _ -> }

            dialog.show()
        }

    }
    fun showWinner() {

        if (playerPoints >= targetScore || cpuPoints >= targetScore) {
            val rollButton: Button = findViewById(R.id.button)
            rollButton.isEnabled = false
            val score: Button = findViewById(R.id.button2)
            score.isEnabled=false
            val rethrow: Button = findViewById(R.id.rethrow)
            rethrow.isEnabled=false
            var gameMode: Button = findViewById(R.id.gamemode)
            gameMode.isEnabled=false



            if (playerPoints > cpuPoints) {
                humanWins++
                Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).apply {
                    view?.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                    show()
                }
            } else if (cpuPoints > playerPoints) {
                computerWins++
                Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).apply {
                    view?.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                    show()
                }
            } else {
                // in the case of a tie only the roll button is enabled and the rest of the buttons are disabled

                rollButton.isEnabled=true
                tiebreakfucnc()
                //tie break function is used break the tie hence the one who scores more will be anounced as winner

            }
            updateWinCount()
        }






    }

    private fun tiebreakfucnc() {
        if (cpuPoints < playerPoints) {
            humanWins++
            Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).apply {
                view?.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                show()
            }
        }else if (cpuPoints > playerPoints) {
            computerWins++
            Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).apply {
                view?.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                show()
            }
        }

    }
//functions to passing the random numbers to die faces

    fun getDiceImageResource(diceValue: Int): Int {
        return when (diceValue) {
            1 -> R.drawable.die_face_1
            2 -> R.drawable.die_face_2
            3 -> R.drawable.die_face_3
            4 -> R.drawable.die_face_4
            5 -> R.drawable.die_face_5
            6 -> R.drawable.die_face_6
            else -> R.drawable.die_face_6
        }
    }
    fun setImageCPU(num: Int) {
        when (num) {
            1 -> iv_cpu!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_cpu!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_cpu!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_cpu!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_cpu!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_cpu!!.setImageResource(R.drawable.die_face_6)

        }
    }
    fun setImageCPU2(num: Int) {
        when (num) {
            1 -> iv_cpu2!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_cpu2!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_cpu2!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_cpu2!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_cpu2!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_cpu2!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImageCPU3(num: Int) {
        when (num) {
            1 -> iv_cpu3!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_cpu3!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_cpu3!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_cpu3!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_cpu3!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_cpu3!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImageCPU4(num: Int) {
        when (num) {
            1 -> iv_cpu4!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_cpu4!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_cpu4!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_cpu4!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_cpu4!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_cpu4!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImageCPU5(num: Int) {
        when (num) {
            1 -> iv_cpu5!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_cpu5!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_cpu5!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_cpu5!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_cpu5!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_cpu5!!.setImageResource(R.drawable.die_face_6)
        }
    }

    fun setImagePlayer(num: Int) {
        when (num) {
            1 -> iv_player!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_player!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_player!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_player!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_player!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_player!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImagePlayer2(num: Int) {
        when (num) {
            1 -> iv_player2!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_player2!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_player2!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_player2!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_player2!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_player2!!.setImageResource(R.drawable.die_face_6)
        }
    }

    fun setImagePlayer3(num: Int) {
        when (num) {
            1 -> iv_player3!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_player3!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_player3!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_player3!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_player3!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_player3!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImagePlayer4(num: Int) {
        when (num) {
            1 -> iv_player4!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_player4!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_player4!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_player4!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_player4!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_player4!!.setImageResource(R.drawable.die_face_6)
        }
    }
    fun setImagePlayer5(num: Int) {
        when (num) {
            1 -> iv_player5!!.setImageResource(R.drawable.die_face_1)
            2 -> iv_player5!!.setImageResource(R.drawable.die_face_2)
            3 -> iv_player5!!.setImageResource(R.drawable.die_face_3)
            4 -> iv_player5!!.setImageResource(R.drawable.die_face_4)
            5 -> iv_player5!!.setImageResource(R.drawable.die_face_5)
            6 -> iv_player5!!.setImageResource(R.drawable.die_face_6)
        }
    }
    //dialog box to get the user input of the target score
    fun setTargetScoreDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_set_target_score)
        dialog.setCancelable(true)
        val targetScoreEditText = dialog.findViewById<EditText>(R.id.target_score_edit_text)
        targetScoreEditText.setText(targetScore.toString())
        val okButton = dialog.findViewById<Button>(R.id.ok_button)
        okButton.setOnClickListener {
            val newTargetScore = targetScoreEditText.text.toString().toIntOrNull()
            if (newTargetScore != null && newTargetScore > 0) {
                targetScore = newTargetScore
                tv_cpu!!.text = "CPU: $cpuPoints"
                tv_player!!.text = "YOU: $playerPoints"
                updateScoreboard()
                val rollButton: Button = findViewById(R.id.button)
                rollButton.isEnabled = true
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Invalid target score!", Toast.LENGTH_LONG).show()
            }


        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveWinCount()
    }

    fun loadWinCount() {
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        humanWins = prefs.getInt("humanWins", 0)
        computerWins = prefs.getInt("computerWins", 0)
    }


    fun saveWinCount() {
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt("humanWins", humanWins)
            putInt("computerWins", computerWins)
            apply()
        }
    }
    fun updateWinCount() {
        val winCountTextView = findViewById<TextView>(R.id.win_count_text_view)
        winCountTextView.text = "H:$humanWins/C:$computerWins"
    }

    private fun updateScoreboard() {
        targetScoreTextView.text = "Target: $targetScore"

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }
}

