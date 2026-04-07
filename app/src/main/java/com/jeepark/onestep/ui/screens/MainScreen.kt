package com.jeepark.onestep.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import com.jeepark.onestep.util.CHICK_PIXELS; import com.jeepark.onestep.util.CHICK_COLORS
import com.jeepark.onestep.util.TURTLE_PIXELS; import com.jeepark.onestep.util.TURTLE_COLORS
import com.jeepark.onestep.util.CAT_PIXELS; import com.jeepark.onestep.util.CAT_COLORS
import com.jeepark.onestep.util.DOG_PIXELS; import com.jeepark.onestep.util.DOG_COLORS
import com.jeepark.onestep.util.BLUEBIRD_PIXELS; import com.jeepark.onestep.util.BLUEBIRD_COLORS
import com.jeepark.onestep.util.BEAR_PIXELS; import com.jeepark.onestep.util.BEAR_COLORS
import com.jeepark.onestep.util.HORSE_PIXELS; import com.jeepark.onestep.util.HORSE_COLORS
import com.jeepark.onestep.util.DOLPHIN_PIXELS; import com.jeepark.onestep.util.DOLPHIN_COLORS
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

// ===== 드로잉 헬퍼 =====

private fun DrawScope.drawPixelArt(
    pixels: Array<IntArray>,
    colors: List<Color>,
    topLeft: Offset,
    pixelSize: Float
) {
    pixels.forEachIndexed { row, cols ->
        cols.forEachIndexed { col, ci ->
            if (ci > 0 && ci < colors.size) {
                drawRect(
                    color   = colors[ci],
                    topLeft = Offset(topLeft.x + col * pixelSize, topLeft.y + row * pixelSize),
                    size    = Size(pixelSize, pixelSize)
                )
            }
        }
    }
}

private fun DrawScope.drawTree(centerX: Float, bottomY: Float, density: Density) {
    val trunkW = with(density) { 12.dp.toPx() }
    val trunkH = with(density) { 55.dp.toPx() }
    val leafR  = with(density) { 30.dp.toPx() }
    drawRect(Color(0xFF8B5E3C), topLeft = Offset(centerX - trunkW / 2, bottomY - trunkH), size = Size(trunkW, trunkH))
    drawCircle(Color(0xFF5A9048), leafR,         Offset(centerX, bottomY - trunkH - leafR * 0.55f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX - leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX + leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
}

private fun DrawScope.drawCloud(center: Offset, radius: Float) {
    val c = Color.White.copy(alpha = 0.88f)
    drawCircle(c, radius,         center)
    drawCircle(c, radius * 0.72f, Offset(center.x + radius * 0.88f, center.y + radius * 0.12f))
    drawCircle(c, radius * 0.72f, Offset(center.x - radius * 0.80f, center.y + radius * 0.18f))
}

private fun DrawScope.drawFence(y: Float, screenW: Float, density: Density) {
    val postW   = with(density) { 5.dp.toPx() }
    val postH   = with(density) { 22.dp.toPx() }
    val spacing = with(density) { 22.dp.toPx() }
    val railH   = with(density) { 2.dp.toPx() }
    val color   = Color(0xFFD4B880)
    var x = 0f
    while (x < screenW) {
        drawRect(color, topLeft = Offset(x, y - postH), size = Size(postW, postH))
        x += spacing
    }
    drawRect(color, topLeft = Offset(0f, y - postH * 0.72f), size = Size(screenW, railH))
    drawRect(color, topLeft = Offset(0f, y - postH * 0.30f), size = Size(screenW, railH))
}

private fun tierSkyColor(tier: Int): Color = when (tier) {
    0    -> Color(0xFFB0CCE8)
    1    -> Color(0xFFA4C6E4)
    2    -> Color(0xFF98C0E0)
    3    -> Color(0xFF8CBADC)
    4    -> Color(0xFF70A8D4)
    5    -> Color(0xFF5484B0)
    else -> Color(0xFFF5E5A0) // 티어 6: 황금빛
}


// ===== 메인 화면 =====

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToMainQuest: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToSetting: () -> Unit,
) {
    val context = LocalContext.current
    val vm: MainViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as android.app.Application
        )
    )
    val user by vm.user.collectAsState()
    val questList by vm.questList.collectAsState()
    val isLoadingQuests by vm.isLoadingQuests.collectAsState()
    val activeQuest by vm.activeQuest.collectAsState()
    val activeIsInside by vm.activeIsInside.collectAsState()
    val tier = (user?.tier ?: 0).coerceIn(0, 7)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var showInputDialog   by remember { mutableStateOf(false) }
    var showSuggestDialog by remember { mutableStateOf(false) }
    var showVerifyDialog  by remember { mutableStateOf(false) }
    var selectedMood      by remember { mutableStateOf(-1) }
    var isInside          by remember { mutableStateOf(true) }
    var currentQuestIndex by remember { mutableStateOf(0) }
    var currentQuest      by remember { mutableStateOf<Quest?>(null) }
    var showTierUp        by remember { mutableStateOf(false) }
    val dragOffset        = remember { Animatable(0f) }
    val screenWidthPx     = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    LaunchedEffect(showTierUp) {
        if (showTierUp) {
            snackbarHostState.showSnackbar("새 친구가 나타났어요!")
            showTierUp = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {

        // 스와이프 미리보기: CollectionScreen 배경색
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationX = screenWidthPx + dragOffset.value }
                .background(Color(0xFFFDF8F0))
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { translationX = dragOffset.value }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart  = { scope.launch { dragOffset.snapTo(0f) } },
                    onDragCancel = { scope.launch { dragOffset.animateTo(0f, spring()) } },
                    onDragEnd    = {
                        scope.launch {
                            if (dragOffset.value < -screenWidthPx * 0.35f) {
                                dragOffset.animateTo(-screenWidthPx, tween(200))
                                onNavigateToProgress()
                            } else {
                                dragOffset.animateTo(0f, spring())
                            }
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            dragOffset.snapTo((dragOffset.value + dragAmount).coerceAtMost(0f))
                        }
                    }
                )
            }
    ) {
        ParkBackground(tier = tier)

        // 활성 퀘스트가 없을 때만 퀘스트 버튼 표시
        if (activeQuest == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                QuestButton(tier = tier) { showInputDialog = true }
            }
        }

        // 활성 퀘스트 카드 (하단)
        activeQuest?.let { quest ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ActiveQuestCard(
                    quest      = quest,
                    isInside   = activeIsInside,
                    onGiveUp   = { vm.saveGiveUpQuest(quest); vm.clearActiveQuest() },
                    onComplete = { showVerifyDialog = true }
                )
            }
        }

        // 우상단 설정 버튼
        IconButton(
            onClick  = onNavigateToSetting,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 4.dp)
        ) {
            Icon(
                imageVector        = Icons.Default.Menu,
                contentDescription = "설정",
                tint               = Color.White,
                modifier           = Modifier.size(28.dp)
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 110.dp)
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0xFF5A9848),
                contentColor = Color.White
            )
        }
    } // inner Box (draggable content)
    }  // outer Box

    // ---- 다이얼로그 ----
    if (showInputDialog) {
        QuestInputDialog(
            isLoading = isLoadingQuests,
            onDismiss = { if (!isLoadingQuests) showInputDialog = false },
            onSearch  = { mood, inside ->
                selectedMood = mood
                isInside     = inside
                vm.loadFilteredQuests(
                    mood     = mood + 1,
                    isInside = inside,
                    onReady  = { quests ->
                        showInputDialog   = false
                        currentQuestIndex = 0
                        currentQuest      = quests.firstOrNull()
                        if (currentQuest != null) showSuggestDialog = true
                        else Toast.makeText(context, "퀘스트를 찾지 못했어요", Toast.LENGTH_SHORT).show()
                    },
                    onError  = { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )
    }

    currentQuest?.let { quest ->
        if (showSuggestDialog) {
            QuestSuggestDialog(
                quest     = quest,
                onDismiss = { showSuggestDialog = false },
                onSkip    = {
                    if (questList.isNotEmpty()) {
                        currentQuestIndex = (currentQuestIndex + 1) % questList.size
                        currentQuest = questList[currentQuestIndex]
                    }
                },
                onAccept  = {
                    showSuggestDialog = false
                    vm.startQuest(quest, isInside)
                }
            )
        }
    }

    activeQuest?.let { quest ->
        if (showVerifyDialog) {
            QuestVerifyDialog(
                quest     = quest,
                onDismiss = { showVerifyDialog = false },
                onSubmit  = { answer ->
                    vm.saveCompletedQuest(
                        quest     = quest,
                        answer    = answer,
                        isInside  = activeIsInside,
                        onTierUp  = { showTierUp = true },
                        onDone    = {
                            showVerifyDialog = false
                            vm.clearActiveQuest()
                        }
                    )
                }
            )
        }
    }
}

// ===== 공원 배경 Canvas =====

@Composable
internal fun ParkBackground(tier: Int, modifier: Modifier = Modifier) {
    val density = LocalDensity.current

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val skyH    = h * 0.35f
        val grass1  = h * 0.45f
        val grass2  = h * 0.62f
        val groundY = h * 0.58f
        val pxSz    = with(density) { 4.5.dp.toPx() }

        // 하늘
        drawRect(tierSkyColor(tier), topLeft = Offset.Zero, size = Size(w, skyH))

        // 잔디 3겹
        drawRect(Color(0xFF6EB04A), topLeft = Offset(0f, skyH),    size = Size(w, grass1 - skyH))
        drawRect(Color(0xFF56A038), topLeft = Offset(0f, grass1),   size = Size(w, grass2 - grass1))
        drawRect(Color(0xFF468028), topLeft = Offset(0f, grass2),   size = Size(w, h - grass2))

        // 태양 (티어 3+)
        if (tier >= 3) {
            drawCircle(Color(0xFFF8E840), with(density) { 26.dp.toPx() }, Offset(w * 0.83f, skyH * 0.32f))
        }

        // 구름 (티어 3+)
        if (tier >= 3) {
            drawCloud(Offset(w * 0.20f, skyH * 0.22f), with(density) { 22.dp.toPx() })
            drawCloud(Offset(w * 0.58f, skyH * 0.42f), with(density) { 16.dp.toPx() })
        }

        // 왼쪽 나무 (티어 1+)
        if (tier >= 1) drawTree(w * 0.20f, groundY, density)

        // 오른쪽 나무 (티어 2+)
        if (tier >= 2) drawTree(w * 0.80f, groundY, density)

        // 울타리 (티어 2+)
        if (tier >= 2) drawFence(groundY + with(density) { 4.dp.toPx() }, w, density)

        // 연못 (티어 4+)
        if (tier >= 4) {
            val cx = w * 0.50f
            val cy = groundY + with(density) { 16.dp.toPx() }
            val rx = with(density) { 48.dp.toPx() }
            val ry = with(density) { 16.dp.toPx() }
            drawOval(Color(0xFF70B8E0), topLeft = Offset(cx - rx, cy - ry), size = Size(rx * 2, ry * 2))
            drawOval(Color(0xFF88C8F0), topLeft = Offset(cx - rx * 0.7f, cy - ry * 0.6f), size = Size(rx * 1.4f, ry * 1.2f))
        }

        // 꽃 (티어 5+)
        if (tier >= 5) {
            val flowerR = with(density) { 5.dp.toPx() }
            val flowers = listOf(
                Offset(w * 0.10f, groundY - with(density) { 3.dp.toPx() }) to Color(0xFFE85858),
                Offset(w * 0.16f, groundY + with(density) { 7.dp.toPx() }) to Color(0xFFF09840),
                Offset(w * 0.36f, groundY + with(density) { 4.dp.toPx() }) to Color(0xFFD04878),
                Offset(w * 0.63f, groundY + with(density) { 3.dp.toPx() }) to Color(0xFFE85858),
                Offset(w * 0.87f, groundY - with(density) { 2.dp.toPx() }) to Color(0xFFF09840),
                Offset(w * 0.92f, groundY + with(density) { 8.dp.toPx() }) to Color(0xFFD04878),
            )
            flowers.forEach { (pos, color) -> drawCircle(color, flowerR, pos) }
        }

        // 동물들 (티어별 해금)
        val chickH    = CHICK_PIXELS.size    * pxSz
        val turtleH   = TURTLE_PIXELS.size   * pxSz
        val catH      = CAT_PIXELS.size      * pxSz
        val dogH      = DOG_PIXELS.size      * pxSz
        val bluebirdH = BLUEBIRD_PIXELS.size * pxSz
        val bearH     = BEAR_PIXELS.size     * pxSz
        val horseH    = HORSE_PIXELS.size    * pxSz
        val dolphinH  = DOLPHIN_PIXELS.size  * pxSz

        // 0: 병아리 — 항상 등장
        drawPixelArt(CHICK_PIXELS,    CHICK_COLORS,    Offset(w * 0.72f, groundY - chickH),                             pxSz)
        // 1: 거북이
        if (tier >= 1) drawPixelArt(TURTLE_PIXELS,   TURTLE_COLORS,   Offset(w * 0.10f, groundY - turtleH),             pxSz)
        // 2: 고양이
        if (tier >= 2) drawPixelArt(CAT_PIXELS,      CAT_COLORS,      Offset(w * 0.48f, groundY - catH),                pxSz)
        // 3: 강아지
        if (tier >= 3) drawPixelArt(DOG_PIXELS,      DOG_COLORS,      Offset(w * 0.62f, groundY - dogH),                pxSz)
        // 4: 파랑새 — 하늘에 배치
        if (tier >= 4) drawPixelArt(BLUEBIRD_PIXELS, BLUEBIRD_COLORS, Offset(w * 0.55f, groundY - with(density) { 90.dp.toPx() } - bluebirdH), pxSz)
        // 5: 곰
        if (tier >= 5) drawPixelArt(BEAR_PIXELS,     BEAR_COLORS,     Offset(w * 0.05f, groundY - bearH),               pxSz)
        // 6: 말
        if (tier >= 6) drawPixelArt(HORSE_PIXELS,    HORSE_COLORS,    Offset(w * 0.28f, groundY - horseH),              pxSz)
        // 7: 돌고래 — 하늘에 배치
        if (tier >= 7) drawPixelArt(DOLPHIN_PIXELS,  DOLPHIN_COLORS,  Offset(w * 0.18f, groundY - with(density) { 120.dp.toPx() } - dolphinH), pxSz)
    }
}

// ===== 하단 퀘스트 버튼 =====

@Composable
private fun QuestButton(tier: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5A9848)),
        border = BorderStroke(1.5.dp, Color.White.copy(alpha = 0.22f)),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text       = "새 퀘스트 받기",
                fontSize   = 15.sp,
                color      = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ===== 퀘스트 조건 다이얼로그 =====

@Composable
private fun QuestInputDialog(
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onSearch: (mood: Int, isInside: Boolean) -> Unit
) {
    var isInside      by remember { mutableStateOf(true) }
    var selectedMood  by remember { mutableStateOf(-1) }
    var visible       by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val scale by animateFloatAsState(if (visible) 1f else 0.94f, animationSpec = tween(200), label = "scale")
    val alpha by animateFloatAsState(if (visible) 1f else 0f,    animationSpec = tween(200), label = "alpha")

    val moods = listOf("매우\n나쁨", "나쁨", "보통", "좋음", "매우\n좋음")

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier  = Modifier.padding(horizontal = 18.dp, vertical = 22.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    "퀘스트 조건 설정",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color(0xFF3A3228),
                    modifier   = androidx.compose.ui.Modifier.padding(bottom = 18.dp)
                )

                // 장소 섹션
                Text("장소", fontSize = 10.sp, color = Color(0xFF8A7A60), letterSpacing = 1.2.sp,
                    modifier = androidx.compose.ui.Modifier.padding(bottom = 7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 18.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LocationChip("실내",  isInside,  Modifier.weight(1f)) { if (!isLoading) isInside = true }
                    LocationChip("실외", !isInside,  Modifier.weight(1f)) { if (!isLoading) isInside = false }
                }

                // 기분 섹션
                Text("지금 기분", fontSize = 10.sp, color = Color(0xFF8A7A60), letterSpacing = 1.2.sp,
                    modifier = androidx.compose.ui.Modifier.padding(bottom = 7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    moods.forEachIndexed { i, mood ->
                        MoodChip(mood, selectedMood == i, Modifier.weight(1f)) { if (!isLoading) selectedMood = i }
                    }
                }

                // 퀘스트 찾기 버튼
                Button(
                    onClick  = { if (selectedMood >= 0 && !isLoading) onSearch(selectedMood, isInside) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled  = selectedMood >= 0 && !isLoading,
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor         = Color(0xFF6A9858),
                        disabledContainerColor = Color(0xFFB8B0A0)
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(13.dp)
                ) {
                    if (isLoading) {
                        androidx.compose.material3.CircularProgressIndicator(
                            modifier  = Modifier.then(androidx.compose.ui.Modifier.then(
                                androidx.compose.ui.Modifier.size(18.dp)
                            )),
                            color     = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            "퀘스트 찾기",
                            fontSize   = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color      = if (selectedMood >= 0) Color.White else Color(0xFFECE8E0)
                        )
                    }
                }

                // 취소
                Text(
                    "취소",
                    modifier  = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clickable(
                            indication        = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { if (!isLoading) onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

@Composable
private fun LocationChip(text: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0xFF6A9858) else Color(0xFFF0ECE0))
            .border(1.dp, if (selected) Color(0xFF508040) else Color(0xFFD4CDB8), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = if (selected) Color.White else Color(0xFF8A7A60), fontSize = 13.sp)
    }
}

@Composable
private fun MoodChip(text: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Color(0xFFD4EAD0) else Color(0xFFF0ECE0))
            .border(1.dp, if (selected) Color(0xFF7AB870) else Color(0xFFD4CDB8), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color      = if (selected) Color(0xFF2D5A2D) else Color(0xFF7A6E60),
            fontSize   = 10.sp,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
            textAlign  = TextAlign.Center
        )
    }
}

// ===== 퀘스트 추천 다이얼로그 =====

@Composable
private fun QuestSuggestDialog(
    quest: Quest,
    onDismiss: () -> Unit,
    onSkip: () -> Unit,
    onAccept: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val scale by animateFloatAsState(if (visible) 1f else 0.94f, animationSpec = tween(200), label = "scale")
    val alpha by animateFloatAsState(if (visible) 1f else 0f,    animationSpec = tween(200), label = "alpha")

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(quest.questName, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3A3228), lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp))

                // 난이도 + 경험치 행
                Row(
                    modifier          = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRating(quest.difficulty)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("+${quest.questEXP} XP", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF5A7A30))
                }

                // 버튼 행
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick  = onSkip,
                        modifier = Modifier.weight(1f),
                        shape    = RoundedCornerShape(12.dp),
                        border   = BorderStroke(1.dp, Color(0xFFD4CDB8)),
                        colors   = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF0ECE0), contentColor = Color(0xFF6A6058))
                    ) { Text("넘기기", fontSize = 13.sp) }

                    Button(
                        onClick  = onAccept,
                        modifier = Modifier.weight(1f),
                        shape    = RoundedCornerShape(12.dp),
                        colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                    ) { Text("수락하기", fontSize = 13.sp, color = Color.White) }
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

// ===== 퀘스트 수행 다이얼로그 =====

@Composable
private fun ActiveQuestCard(
    quest: Quest,
    isInside: Boolean,
    onGiveUp: () -> Unit,
    onComplete: () -> Unit
) {
    Card(
        modifier  = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth(),
        shape     = RoundedCornerShape(22.dp),
        colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 28.dp, horizontal = 22.dp),
        ) {
            Text(
                quest.questName,
                fontSize   = 22.sp,
                fontWeight = FontWeight.Medium,
                color      = Color(0xFF3A3228),
                lineHeight = 32.sp,
                modifier   = Modifier.padding(bottom = 24.dp)
            )

            Row(
                modifier          = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StarRating(quest.difficulty, starSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text("+${quest.questEXP} XP", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF5A7A30))
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier.padding(bottom = 28.dp)
            ) {
                QuestBadge("Lv.${quest.difficulty}", Color(0xFFE8EAD4), Color(0xFF5A6030))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick  = onGiveUp,
                    modifier = Modifier.weight(1f).defaultMinSize(minHeight = 52.dp),
                    shape    = RoundedCornerShape(12.dp),
                    border   = BorderStroke(1.dp, Color(0xFFD4CDB8)),
                    colors   = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF0ECE0), contentColor = Color(0xFF8A7068))
                ) { Text("포기", fontSize = 13.sp) }

                Button(
                    onClick  = onComplete,
                    modifier = Modifier.weight(2f).defaultMinSize(minHeight = 52.dp),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                ) { Text("완료했어요", fontSize = 14.sp, color = Color.White) }
            }
        }
    }
}

@Composable
private fun QuestBadge(text: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(vertical = 5.dp, horizontal = 12.dp)
    ) {
        Text(text, fontSize = 12.sp, color = textColor)
    }
}

// ===== 퀘스트 완료 확인 다이얼로그 =====

@Composable
private fun QuestVerifyDialog(
    quest: Quest,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var answer  by remember { mutableStateOf("") }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier  = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("퀘스트 완료 확인", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3A3228))

                // 질문 박스
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0ECE0), RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp, vertical = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("질문", fontSize = 10.sp, color = Color(0xFF8A7A60))
                    Text(quest.confirmQuestion, fontSize = 13.sp, color = Color(0xFF3A3228), lineHeight = 20.sp)
                }

                // 답변 입력
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("내 답변", fontSize = 10.sp, color = Color(0xFF8A7A60))
                    OutlinedTextField(
                        value         = answer,
                        onValueChange = { answer = it },
                        modifier      = Modifier.fillMaxWidth().defaultMinSize(minHeight = 76.dp),
                        maxLines      = 4,
                        shape         = RoundedCornerShape(12.dp),
                        colors        = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor    = Color(0xFFD4CCB8),
                            unfocusedBorderColor  = Color(0xFFD4CCB8),
                            focusedContainerColor   = Color(0xFFF5F0E8),
                            unfocusedContainerColor = Color(0xFFF5F0E8)
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color(0xFF3A3228))
                    )
                }

                // 제출 버튼
                Button(
                    onClick = {
                        if (answer.isBlank()) {
                            Toast.makeText(context, "답변을 입력해주세요", Toast.LENGTH_SHORT).show()
                        } else {
                            onSubmit(answer.trim())
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                ) {
                    Text("제출하기", color = Color.White, fontSize = 14.sp)
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

// ===== 공통 컴포넌트 =====

@Composable
private fun StarRating(level: Int, starSize: TextUnit = 16.sp) {
    Row {
        repeat(5) { i ->
            Text("★", fontSize = starSize, color = if (i < level) Color(0xFFF0C030) else Color(0xFFE0D8C8))
        }
    }
}
