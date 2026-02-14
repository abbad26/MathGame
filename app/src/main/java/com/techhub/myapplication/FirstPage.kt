package com.techhub.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(navController: NavController) {

    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = colorResource(R.color.brown))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Math Game", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.brown),
                    titleContentColor = Color.White
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(
                        painter = painterResource(R.drawable.first_page),
                        contentScale = ContentScale.FillBounds
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                //Addition Button
                Button(
                    onClick = {
                        navController.navigate("SecondPage/add")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.brown)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(
                        text = "Addition",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Subtraction button
                Button(
                    onClick = {
                        navController.navigate("SecondPage/sub")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.brown)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(
                        text = "Subtraction",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Multiplication
                Button(
                    onClick = {
                        navController.navigate("SecondPage/multi")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.brown)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(
                        text = "Multiplication",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Division
                Button(
                    onClick = {
                        navController.navigate("SecondPage/div")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.brown)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp),
                    modifier = Modifier.size(250.dp, 100.dp)
                ) {
                    Text(
                        text = "Division",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}
