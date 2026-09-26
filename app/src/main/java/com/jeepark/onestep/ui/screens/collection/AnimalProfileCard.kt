package com.jeepark.onestep.ui.screens.collection

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.util.ANIMAL_NAMES
import com.jeepark.onestep.util.PixelAnimalRenderer
import com.jeepark.onestep.util.animalMessages
import com.jeepark.onestep.util.animalProfiles

/** 진척도 화면에서 선택된 해금 동물의 프로필을 보여주는 카드. */
@Composable
fun AnimalProfileCard(
    animalIndex: Int,
    metDateLabel: String,
    modifier: Modifier = Modifier
) {
    val profile = animalProfiles[animalIndex] ?: return
    val quote = animalMessages[animalIndex]?.firstOrNull() ?: ""

    FlatCard(modifier = modifier.fillMaxWidth(), cornerRadius = 26.dp) {
        Column(modifier = Modifier.padding(20.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(56.dp)) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        with(PixelAnimalRenderer) {
                            drawAnimalInBox(animalIndex, Offset.Zero, size.width)
                        }
                    }
                }
                Box(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = ANIMAL_NAMES.getOrElse(animalIndex) { "???" },
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingText
                    )
                    Text(
                        text = "처음 만난 날 · $metDateLabel",
                        fontSize = 11.sp,
                        color = MutedText,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            Box(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SecondaryBackground)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(text = "\"$quote\"", fontSize = 13.sp, color = HeadingText)
                }
            }

            ProfileInfoRow(label = "성격", value = profile.personality)
            ProfileInfoRow(label = "좋아하는 것", value = profile.likes)
            ProfileInfoRow(label = "사는 곳", value = profile.habitat)
        }
    }
}

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.5.sp, color = MutedText)
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = HeadingText)
    }
}
