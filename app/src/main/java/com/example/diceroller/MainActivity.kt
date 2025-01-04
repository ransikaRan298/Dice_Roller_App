package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // State for dice result, roll count, total marks, and reset status
    var result by remember { mutableStateOf(1) }
    var rollCount by remember { mutableStateOf(0) }
    var totalMarks by remember { mutableStateOf(0) }
    var isReset by remember { mutableStateOf(false) }

    // Dice image resource based on result
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Reset function
    fun reset() {
        result = 1
        rollCount = 0
        totalMarks = 0
        isReset = true
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App header with "Dice Roller" title
        Text(
            text = "Dice Roller",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Dice Image
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        // Spacer for better UI spacing
        Spacer(modifier = Modifier.height(16.dp))

        // Display roll count
        Text(text = "Roll Count: $rollCount", fontSize = 18.sp, color = Color.Black)

        // Display total marks
        Text(text = "Total Marks: $totalMarks", fontSize = 18.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // Roll button with a modern design
        Button(
            onClick = {
                result = (1..6).random()
                rollCount++
                totalMarks += result
                isReset = false
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }

        // Spacer
        Spacer(modifier = Modifier.height(16.dp))

        // Reset button with a modern design
        Button(
            onClick = {
                reset()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Reset", fontSize = 24.sp)
        }

        // Spacer
        Spacer(modifier = Modifier.height(16.dp))

        // Display reset status
        if (isReset) {
            Text(text = "The dice has been reset.", fontSize = 16.sp, color = Color.Black)
        }
    }
}
