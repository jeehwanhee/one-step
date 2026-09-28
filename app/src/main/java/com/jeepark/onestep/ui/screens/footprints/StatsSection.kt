package com.jeepark.onestep.ui.screens.footprints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.ui.theme.CardSurface
import java.util.Locale

// 발자취(FootprintsScreen)의 "통계" 탭에서 쓰는 통계 섹션.
// 값 계산은 data/model/QuestStats.kt(순수 함수)에 있고, 이 파일은 표시만 맡는다.

@Composable
fun StatsColumn(
    completedCount: Int,
    streakDays: Int,
    maxStreakDays: Int,
    startDate: String,
    dday: Int,
    totalExp: Int,
    avgDifficulty: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardSurface)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatRow(label = "현재 연속 활동일",   value = "${streakDays}일")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFEAE3D2))
        )
        StatRow(label = "최장 연속 활동일", value = "${maxStreakDays}일")
        StatRow(
            label = "활동 시작일",
            value = if (startDate.isNotEmpty()) "$startDate (D+$dday)" else "-"
        )

        StatRow(label = "총 획득 경험치", value = "${totalExp} XP")
        StatRow(label = "완료한 퀘스트", value = "${completedCount}개")
        StatRow(
            label = "평균 난이도",
            value = if (avgDifficulty > 0) String.format(Locale.getDefault(), "%.1f", avgDifficulty) else "-"
        )
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text     = label,
            fontSize = 13.sp,
            color    = Color(0xFF6A6058)
        )
        Text(
            text       = value,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color      = Color(0xFF2A2A2A)
        )
    }
}
