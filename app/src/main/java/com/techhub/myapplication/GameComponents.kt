package com.techhub.myapplication

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun TextForQuestion(text: String){

    Text(
        text = text,
        fontSize = 16.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = colorResource(R.color.brown))
            .size(300.dp, 75.dp)
            .wrapContentSize()
    )
}

@Composable
fun TextFieldForAnswer(text: MutableState<String>){

    TextField(
        value = text.value,
        onValueChange = {text.value = it},
        label = {Text(text = "Enter your answer...")},
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = colorResource(R.color.brown),
            unfocusedContainerColor = colorResource(R.color.brown),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        modifier = Modifier
            .size(300.dp, 75.dp),
        textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
        shape = RoundedCornerShape(0),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun ButtonOkNext(buttonText : String, myOnClick:() -> Unit, isEnabled : Boolean){

    Button(
        onClick = myOnClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(2.dp, color = colorResource(R.color.brown)),
        modifier = Modifier.width(150.dp)
    ) {
        Text(text = buttonText, fontSize = 24.sp, color = colorResource(R.color.brown))
    }
}