package com.techhub.myapplication

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ResultPage(navController: NavController, score : Int){

    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = colorResource(R.color.dar_grey))

    val myContext = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.first_page), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(150.dp))

        Text(text = "Congratulations", fontSize = 25.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Your score: $score", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Button(
                onClick = {

                    navController.popBackStack("FirstPage", inclusive = false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(2.dp, color = colorResource(R.color.brown)),
                modifier = Modifier.width(150.dp)) {
                Text(text = "Play Again", fontSize = 20.sp, color = Color.Black)
            }


            Button(
                onClick = {

                    myContext.finish()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(2.dp, color = colorResource(R.color.brown)),
                modifier = Modifier.width(150.dp)) {
                Text(text = "Exit", fontSize = 20.sp, color = Color.Black)
            }
        }
    }

}