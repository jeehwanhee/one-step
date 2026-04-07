package com.jeepark.onestep.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

// =============================================
// 동물 순서: 0병아리 1거북이 2고양이 3강아지 4파랑새 5곰 6말 7돌고래
// 모든 동물: 20×20 픽셀
// =============================================

// 0: 병아리 (20×20) — 0=tr 1=노랑 2=황금 3=검정 4=주황빨강 5=밝은노랑 6=진주황
val CHICK_COLORS = listOf(
    Color.Transparent,
    Color(0xFFFFD600),
    Color(0xFFF5A000),
    Color(0xFF1A1A1A),
    Color(0xFFCC3000),
    Color(0xFFFFF880),
    Color(0xFFA06000),
)
val CHICK_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,5,1,1,1,1,1,1,3,4,4,3,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,3,1,1,1,1,4,4,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,3,2,1,1,1,1,1,1,1,1,1,1,2,3,0,0,0,0,0),
    intArrayOf(0,3,1,5,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(3,2,1,1,1,1,1,1,1,1,1,1,1,2,3,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,1,1,1,1,1,1,2,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,3,2,1,2,3,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,4,4,4,3,0,3,4,4,4,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,4,6,3,0,0,0,3,6,4,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 1: 거북이 (20×20) — 0=tr 1=초록 2=진초록 3=검정 4=진갈색셸 5=중간갈색 6=주황갈색 7=밝은하이라이트
val TURTLE_COLORS = listOf(
    Color.Transparent,
    Color(0xFF28A030),
    Color(0xFF1A6020),
    Color(0xFF1A1A1A),
    Color(0xFF7B3000),
    Color(0xFFC06020),
    Color(0xFFE08040),
    Color(0xFFF0A000),
)
val TURTLE_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,3,0,0,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0),
    intArrayOf(3,1,1,3,3,4,4,4,4,4,4,4,4,4,4,3,0,0,0,0),
    intArrayOf(3,2,2,3,4,5,6,5,4,5,6,5,4,5,6,4,3,0,0,0),
    intArrayOf(0,3,3,3,4,6,7,6,5,6,7,6,5,6,7,6,4,3,0,0),
    intArrayOf(0,3,1,3,4,5,6,5,6,7,5,6,5,6,5,6,5,4,3,0),
    intArrayOf(0,3,1,3,4,6,5,7,5,6,5,7,6,5,7,5,6,4,3,0),
    intArrayOf(0,3,2,3,4,5,6,5,6,5,6,5,6,5,6,5,5,4,3,0),
    intArrayOf(0,0,3,3,4,4,4,4,4,4,4,4,4,4,4,4,3,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,3,0,3,1,1,3,0,0),
    intArrayOf(0,0,3,1,2,1,2,1,3,0,0,0,0,0,3,2,1,2,3,0),
    intArrayOf(0,3,2,3,0,3,1,2,3,0,0,0,0,3,2,3,0,3,2,3),
    intArrayOf(3,2,3,0,0,0,3,3,0,0,0,0,3,3,0,0,0,0,3,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 2: 고양이 (20×20) — 0=tr 1=주황갈색 2=진갈색 3=검정 4=크림 5=중간갈색 6=핑크 7=줄무늬
val CAT_COLORS = listOf(
    Color.Transparent,
    Color(0xFFD4855A),
    Color(0xFF7A3A1A),
    Color(0xFF1A1A1A),
    Color(0xFFF0C8A0),
    Color(0xFFE89070),
    Color(0xFFF0A0A0),
    Color(0xFFB06030),
)
val CAT_PIXELS = arrayOf(
    intArrayOf(0,0,0,3,3,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,0,0,0,3,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,5,6,3,0,0,0,3,6,5,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,3,3,3,3,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,5,1,1,1,1,1,1,5,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,1,1,1,1,3,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,6,1,1,4,4,1,1,6,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,4,4,4,4,4,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,5,4,4,4,4,4,5,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,4,4,4,4,4,4,1,1,3,3,3,0,0,0,0),
    intArrayOf(0,0,3,5,1,4,4,4,4,4,5,1,3,2,2,1,3,0,0,0),
    intArrayOf(0,0,3,1,1,1,4,4,4,1,1,1,3,2,1,1,3,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,3,2,1,1,3,0,0,0,0),
    intArrayOf(0,0,3,1,5,1,3,0,3,5,1,3,2,2,3,0,0,0,0,0),
    intArrayOf(0,0,3,5,1,3,0,0,0,3,1,5,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,3,0,0,0,0,3,2,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 3: 강아지 (20×20) — 0=tr 1=황금 2=진갈색 3=검정 4=크림 5=중간황금 6=핑크혀 7=밝은배
val DOG_COLORS = listOf(
    Color.Transparent,
    Color(0xFFD4A040),
    Color(0xFF7A4800),
    Color(0xFF1A1A1A),
    Color(0xFFF0E0A0),
    Color(0xFFE0B060),
    Color(0xFFE06060),
    Color(0xFFF8F8E0),
)
val DOG_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,3,1,4,4,4,4,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,3,1,5,1,1,1,1,5,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,5,1,1,3,0,0,3,1,1,5,3,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,2,2,2,2,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,0,0,0,3,1,2,6,6,2,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,3,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,3,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,7,7,7,7,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,7,7,7,7,7,7,1,1,1,1,3,0,0,0,0),
    intArrayOf(3,1,5,1,5,7,7,7,7,7,5,1,5,1,1,3,0,0,0,0),
    intArrayOf(3,2,1,5,1,7,7,7,7,7,1,5,1,1,1,3,0,0,0,0),
    intArrayOf(0,3,5,1,1,1,7,7,7,1,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,3,1,5,1,1,1,1,1,1,1,1,5,1,3,0,0,0,0,0),
    intArrayOf(0,3,1,1,5,1,0,0,0,1,1,5,1,3,0,0,0,0,0,0),
    intArrayOf(0,3,5,1,3,3,0,0,0,3,3,1,5,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,3,0,0,0,0,3,2,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 4: 파랑새 (20×20) — 0=tr 1=파랑 2=진파랑 3=검정 4=흰색 5=노랑부리 6=밝은파랑
val BLUEBIRD_COLORS = listOf(
    Color.Transparent,
    Color(0xFF2090E0),
    Color(0xFF0060B0),
    Color(0xFF1A1A1A),
    Color(0xFFFFFFFF),
    Color(0xFFF0C020),
    Color(0xFF80C8FF),
)
val BLUEBIRD_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,3,0,0,0,3,1,1,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,1,3,0,3,1,6,1,1,3,0,0,0,0,0,0,0,0),
    intArrayOf(3,2,1,1,1,3,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(3,1,2,1,1,1,3,4,4,1,1,1,1,3,5,5,3,0,0,0),
    intArrayOf(0,3,1,2,1,1,3,4,4,4,4,1,1,3,5,3,0,0,0,0),
    intArrayOf(0,3,1,1,2,3,4,4,4,3,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,1,4,4,1,1,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,1,1,1,1,1,1,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,1,2,1,1,1,1,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(3,2,1,1,1,2,1,1,3,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(3,1,2,1,1,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,2,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 5: 곰 (20×20) — 0=tr 1=밝은갈색 2=진갈색-빨강 3=검정 4=매우진 5=연갈색 6=크림주둥이 7=청남색눈
val BEAR_COLORS = listOf(
    Color.Transparent,
    Color(0xFFC09060),
    Color(0xFF8B5030),
    Color(0xFF1A1A1A),
    Color(0xFF604020),
    Color(0xFFD4A880),
    Color(0xFFE8C080),
    Color(0xFF3040A0),
)
val BEAR_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,5,1,1,1,1,3,3,3,3,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,1,1,5,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,3,1,6,6,6,1,1,1,5,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,3,1,6,7,6,1,1,1,1,5,1,1,1,1,3,0,0,0,0),
    intArrayOf(0,3,1,6,6,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,1,1,1,1,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(0,0,3,5,1,1,1,1,1,1,1,1,1,5,3,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,5,1,1,1,1,1,5,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,2,1,1,1,1,1,1,2,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,2,2,1,1,1,1,2,2,2,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,2,4,3,1,1,3,4,2,4,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,4,3,0,3,2,3,0,3,4,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,2,3,0,0,0,3,0,0,0,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 6: 말 (20×20) — 0=tr 1=체스넛 2=진갈색 3=검정 4=크림/흰색 5=중간체스넛 6=발굽 7=더진체스넛
val HORSE_COLORS = listOf(
    Color.Transparent,
    Color(0xFFC05020),
    Color(0xFF802800),
    Color(0xFF1A1A1A),
    Color(0xFFF0E8D0),
    Color(0xFFE07840),
    Color(0xFF401000),
    Color(0xFFD06030),
)
val HORSE_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,2,1,1,1,1,3,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,1,1,3,1,1,1,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,4,1,1,1,1,1,1,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,5,1,1,1,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,1,1,1,1,1,1,1,3,2,2,3,0,0),
    intArrayOf(0,0,0,3,1,5,1,1,1,1,1,1,1,1,2,2,2,3,0,0),
    intArrayOf(0,0,0,3,7,1,1,1,1,1,1,1,1,3,2,2,3,0,0,0),
    intArrayOf(0,0,0,0,3,1,1,1,1,1,1,1,3,2,2,3,0,0,0,0),
    intArrayOf(0,0,3,1,1,7,1,0,3,7,1,3,0,2,3,0,0,0,0,0),
    intArrayOf(0,0,3,5,1,5,3,0,3,5,1,5,3,2,3,0,0,0,0,0),
    intArrayOf(0,0,3,4,1,3,0,0,3,4,1,3,0,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,4,3,0,0,0,3,4,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,6,3,0,0,0,3,6,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// 7: 돌고래 (20×20) — 0=tr 1=청보라 2=진보라 3=중간보라 4=연하늘 5=흰하이라이트 6=매우진
val DOLPHIN_COLORS = listOf(
    Color.Transparent,
    Color(0xFF5060C0),
    Color(0xFF3040A0),
    Color(0xFF7878D8),
    Color(0xFFA0C0F0),
    Color(0xFFE0F0FF),
    Color(0xFF404090),
)
val DOLPHIN_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,1,1,6,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,6,1,2,1,1,1,1,6,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,6,1,6,2,1,1,1,1,6,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,6,1,1,2,1,1,1,6,6,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,6,1,4,1,1,1,6,6,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,6,1,4,4,1,1,6,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,6,1,4,5,4,1,6,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,6,1,1,4,4,6,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,6,1,1,1,6,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,6,6,1,6,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,6,1,2,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,6,2,6,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0),
    intArrayOf(0,6,1,6,0,0,0,0,6,1,1,2,6,0,0,0,0,0,0,0),
    intArrayOf(6,2,6,0,0,0,0,6,2,1,1,1,2,6,0,0,0,0,0,0),
    intArrayOf(0,6,0,0,0,0,6,1,1,1,1,2,6,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,6,2,1,2,1,6,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,6,2,6,6,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
)

// =============================================
// 공통 렌더러
// =============================================

private val allPixels = listOf(
    CHICK_PIXELS, TURTLE_PIXELS, CAT_PIXELS, DOG_PIXELS,
    BLUEBIRD_PIXELS, BEAR_PIXELS, HORSE_PIXELS, DOLPHIN_PIXELS
)
private val allColors = listOf(
    CHICK_COLORS, TURTLE_COLORS, CAT_COLORS, DOG_COLORS,
    BLUEBIRD_COLORS, BEAR_COLORS, HORSE_COLORS, DOLPHIN_COLORS
)

val ANIMAL_NAMES = listOf("병아리", "거북이", "고양이", "강아지", "파랑새", "곰", "말", "돌고래")

object PixelAnimalRenderer {

    fun DrawScope.drawAnimal(index: Int, topLeft: Offset, pixelSize: Float) {
        val pixels = allPixels[index]
        val colors = allColors[index]
        pixels.forEachIndexed { r, row ->
            row.forEachIndexed { c, ci ->
                if (ci > 0 && ci < colors.size) {
                    drawRect(
                        color   = colors[ci],
                        topLeft = Offset(topLeft.x + c * pixelSize, topLeft.y + r * pixelSize),
                        size    = Size(pixelSize, pixelSize)
                    )
                }
            }
        }
    }

    fun DrawScope.drawAnimalInBox(index: Int, topLeft: Offset, boxSize: Float) {
        val pixels = allPixels[index]
        val cols   = pixels[0].size
        val rows   = pixels.size
        val pixSz  = minOf(boxSize / cols, boxSize / rows)
        val startX = topLeft.x + (boxSize - cols * pixSz) / 2f
        val startY = topLeft.y + (boxSize - rows * pixSz) / 2f
        drawAnimal(index, Offset(startX, startY), pixSz)
    }

    fun pixelWidth(index: Int)  = allPixels[index][0].size
    fun pixelHeight(index: Int) = allPixels[index].size
}
