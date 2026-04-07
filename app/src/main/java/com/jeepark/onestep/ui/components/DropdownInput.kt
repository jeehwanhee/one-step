package com.jeepark.onestep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.black
import com.jeepark.onestep.ui.theme.gray2
import com.jeepark.onestep.ui.theme.text_Bold_20
import com.jeepark.onestep.ui.theme.text_Bold_24
import com.jeepark.onestep.ui.theme.text_Bold_28

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInput(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedText: String,
    onOptionSelected: (Int) -> Unit,
    placeholder: String
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .height(60.dp),
            textStyle = text_Bold_24,
            placeholder = { Text(placeholder, color = gray2, style = text_Bold_20) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = black,
                unfocusedIndicatorColor = black,
                focusedTrailingIconColor = black,
                unfocusedTrailingIconColor = black,
                focusedTextColor = black,
                unfocusedTextColor = black
            ),
            singleLine = true
        )

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))
        ) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                options.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                style = text_Bold_20,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        },
                        onClick = {
                            onOptionSelected(index)
                            expanded = false
                        },
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
                    )

                    if (index < options.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 0.5.dp,
                            color = gray2.copy(alpha = 0.3f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSelectionInput() {
    var selectedText by remember { mutableStateOf("") }
    val options = listOf("매우 나쁨", "나쁨", "보통", "좋음", "매우 좋음")

    DropdownInput(
        options = options,
        selectedText = selectedText,
        onOptionSelected = { index ->
            selectedText = options[index]
        },
        placeholder = "기분을 선택하세요"
    )
}