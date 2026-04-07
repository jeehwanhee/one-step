package com.jeepark.onestep.ui.components


import android.R.attr.onClick
import android.R.attr.text
import android.view.View
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.OneStepTheme
import com.jeepark.onestep.ui.theme.beige1
import com.jeepark.onestep.ui.theme.beige2
import com.jeepark.onestep.ui.theme.black
import com.jeepark.onestep.ui.theme.gray1
import com.jeepark.onestep.ui.theme.text_Bold_20
import com.jeepark.onestep.ui.theme.white

@Composable
fun SelectButton(
    leftText: String,
    rightText: String,
    leftOnClick: () -> Unit,
    rightOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelectedLeft: Boolean = true,

) {
    Row (modifier = modifier
        .fillMaxWidth()
        .height(69.dp)
    ) {
        Button(
            onClick = leftOnClick,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(isSelectedLeft) beige2 else beige1,
                contentColor = black,
            ),
            contentPadding = PaddingValues(10.dp),

        ) {
            Text(
                text = leftText,
                style = text_Bold_20
            )
        }
        Button(
            onClick = rightOnClick,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(!isSelectedLeft) beige2 else beige1,
                contentColor = black,
            ),
            contentPadding = PaddingValues(10.dp),
        ) {
            Text(
                text = rightText,
                style = text_Bold_20
            )
        }
    }
}

@Preview
@Composable
private fun PrevSelectButton() {
    var selectedByMe by remember { mutableStateOf(true) }

    SelectButton(
        leftText = "남자",
        rightText = "여자",
        leftOnClick = {selectedByMe = true},
        rightOnClick = {selectedByMe = false},
        isSelectedLeft = selectedByMe
        )
}