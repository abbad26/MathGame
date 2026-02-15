package com.techhub.myapplication

import android.content.IntentSender
import android.graphics.drawable.Icon
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(navController: NavController, category: String){
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = colorResource(R.color.dar_grey))

    val life = remember { mutableStateOf(3) }
    val score = remember { mutableStateOf(0) }
    val remainingTimeText = remember { mutableStateOf("30") }

    val myContext = LocalContext.current

    val myQuestion = remember { mutableStateOf("") }
    val myAnswer = remember { mutableStateOf("") }
    val isEnabled = remember { mutableStateOf(true) }
    val correctAnswer = remember { mutableStateOf(0) }
    val isChecked = remember { mutableStateOf(true) }

    val totalTimeInMillis = remember { mutableStateOf(15000L) }
    val timer = remember {
        mutableStateOf(
            object : CountDownTimer(totalTimeInMillis.value, 1000){
                override fun onFinish() {
                    cancel()
                    myQuestion.value = "Sorry, time is up!"
                    life.value -= 1
                    isEnabled.value = false
                    isChecked.value = false
                    myAnswer.value = ""
                }

                override fun onTick(millisUntilFinished: Long) {

                    remainingTimeText.value = String.format(Locale.getDefault(), "%02d", millisUntilFinished/1000)
                }

            }.start()
        )
    }

    // LaunchedEffect -> enter the composition
    // SideEffect -> each re-composition
    // DisposableEffect -> leave the re-composition

    LaunchedEffect(key1 = "math", block = {
        val resultList = generateQuestion(category)
        myQuestion.value = resultList[0].toString()
        correctAnswer.value = resultList[1].toString().toInt()
        Log.d("question", myQuestion.value)
    })


    Scaffold(
        topBar = {
            TopAppBar(

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigation icon"
                        )
                    }

                },
                title = {
                    Text(
                        text = when(category){
                            "add" -> "Addition"
                            "sub" -> "Subtraction"
                            "multi" -> "Multiplication"
                            else -> "Division"
                        },
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.brown),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(painterResource(R.drawable.second_page_gm), contentScale = ContentScale.FillBounds),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(text = "Life:", fontSize = 16.sp, color = Color.White)
                    Text(text = life.value.toString(), fontSize = 16.sp, color = Color.White)
                    Text(text = "Score:", fontSize = 16.sp, color = Color.White)
                    Text(text = score.value.toString(), fontSize = 16.sp, color = Color.White)
                    Text(text = "Remaining Time:", fontSize = 16.sp, color = Color.White)
                    Text(text = remainingTimeText.value, fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(30.dp))

                TextForQuestion(text = myQuestion.value)

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldForAnswer(text = myAnswer)

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    if (isChecked.value) {

                        // OK BUTTON
                        ButtonOkNext(
                            buttonText = "Check",
                            myOnClick = {

                                if (myAnswer.value.isEmpty()) {

                                    Toast.makeText(
                                        myContext,
                                        "Write an answer",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {

                                    timer.value.cancel()

                                    if (myAnswer.value.toInt() == correctAnswer.value) {
                                        score.value += 10
                                        myQuestion.value = "Congratulations 🎉"
                                    } else {
                                        life.value -= 1
                                        myQuestion.value = "Sorry, wrong answer ❌"
                                    }

                                    myAnswer.value = ""
                                    isChecked.value = false   // Next show hoga
                                }
                            },
                            isEnabled = true
                        )

                    } else {

                        // NEXT BUTTON
                        ButtonOkNext(
                            buttonText = "Next",
                            myOnClick = {

                                if (life.value == 0) {

                                    Toast.makeText(
                                        myContext,
                                        "Game Over!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate("ResultPage/${score.value}") {
                                        popUpTo("FirstPage") { inclusive = false }
                                    }

                                } else {

                                    val newResultList = generateQuestion(category)
                                    myQuestion.value = newResultList[0].toString()
                                    correctAnswer.value =
                                        newResultList[1].toString().toInt()

                                    timer.value.cancel()
                                    timer.value.start()

                                    isChecked.value = true   // Fir se Check show
                                }
                            },
                            isEnabled = true
                        )
                    }
                }

            }
        }
    )
}