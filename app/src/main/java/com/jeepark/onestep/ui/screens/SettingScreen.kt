package com.jeepark.onestep.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.ui.viewmodels.AuthViewModel

private val S_BG      = Color(0xFFFDF8F0)
private val S_CARD    = Color(0xFFFFFFFF)
private val S_PRIMARY = Color(0xFF5A9848)
private val S_DIVIDER = Color(0xFFF2EDE6)

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit,
    onNavigateToInitQuestion: () -> Unit,
    onNavigateBack: () -> Unit,
    authVm: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val prefs   = remember { context.getSharedPreferences("app_settings", Context.MODE_PRIVATE) }

    var nickname      by remember { mutableStateOf("") }
    var email         by remember { mutableStateOf("") }
    var notifEnabled  by remember { mutableStateOf(prefs.getBoolean("notification_enabled", true)) }
    var showLogout    by remember { mutableStateOf(false) }
    var showDelete    by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val currentUser = Firebase.auth.currentUser
        email = currentUser?.email ?: ""
        val uid = currentUser?.uid ?: return@LaunchedEffect
        Firebase.firestore.collection("users").document(uid).get()
            .addOnSuccessListener { doc -> nickname = doc.getString("nickname") ?: "" }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(S_BG)
            .verticalScroll(rememberScrollState())
    ) {
        // 헤더
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 20.dp, top = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint               = Color(0xFF4A4A4A)
                )
            }
            Text(
                text       = "설정",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF2A2A2A)
            )
        }

        // 프로필 카드
        Card(
            modifier  = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape     = RoundedCornerShape(16.dp),
            colors    = CardDefaults.cardColors(containerColor = S_CARD),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text       = nickname.ifEmpty { "이름 없음" },
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = Color(0xFF2A2A2A)
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text     = email,
                    fontSize = 13.sp,
                    color    = Color(0xFF888888)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        SettingCard {
            SwitchRow(
                label           = "알림",
                checked         = notifEnabled,
                onCheckedChange = {
                    notifEnabled = it
                    prefs.edit().putBoolean("notification_enabled", it).apply()
                }
            )
        }

        Spacer(Modifier.height(36.dp))

        SettingCard {
            ArrowRow("초기 질문 다시 받기") { onNavigateToInitQuestion() }
        }

        Spacer(Modifier.height(36.dp))

        SettingCard {
            PlainRow("로그아웃") { showLogout = true }
        }

        Spacer(Modifier.height(36.dp))

        SettingCard {
            ArrowRow("이용약관 및 개인정보 처리방침") {
                // TODO: 노션 URL 연결
            }
        }

        Spacer(Modifier.height(36.dp))

        SettingCard {
            PlainRow(label = "계정 탈퇴", textColor = Color(0xFFE05050)) { showDelete = true }
        }

        Spacer(Modifier.height(40.dp))
    }

    // 로그아웃 다이얼로그
    if (showLogout) {
        AlertDialog(
            onDismissRequest = { showLogout = false },
            title            = { Text("로그아웃") },
            text             = { Text("로그아웃 하시겠어요?") },
            confirmButton    = {
                TextButton(onClick = {
                    authVm.signOut(context)
                    showLogout = false
                    onNavigateToMain()
                }) { Text("로그아웃", color = Color(0xFFE05050)) }
            },
            dismissButton    = {
                TextButton(onClick = { showLogout = false }) { Text("취소") }
            }
        )
    }

    // 탈퇴 다이얼로그
    if (showDelete) {
        AlertDialog(
            onDismissRequest = { showDelete = false },
            title            = { Text("계정 탈퇴") },
            text             = { Text("탈퇴하면 모든 데이터가 삭제되며\n복구할 수 없어요. 정말 탈퇴하시겠어요?") },
            confirmButton    = {
                TextButton(onClick = {
                    authVm.deleteAccount(
                        context   = context,
                        onSuccess = { showDelete = false; onNavigateToMain() },
                        onFailure = { showDelete = false }
                    )
                }) { Text("탈퇴하기", color = Color(0xFFE05050)) }
            },
            dismissButton    = {
                TextButton(onClick = { showDelete = false }) { Text("취소") }
            }
        )
    }
}

// ───── 재사용 컴포넌트 ─────────────────────────────────

@Composable
private fun SettingCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = S_CARD),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column { content() }
    }
}

@Composable
private fun SwitchRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 15.sp, color = Color(0xFF2A2A2A))
        Switch(
            checked         = checked,
            onCheckedChange = onCheckedChange,
            colors          = SwitchDefaults.colors(
                checkedThumbColor  = Color.White,
                checkedTrackColor  = S_PRIMARY,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFCCCCCC)
            )
        )
    }
}

@Composable
private fun ArrowRow(label: String, onClick: () -> Unit) {
    Row(
        modifier              = Modifier
            .fillMaxWidth()
            .clickable(
                indication        = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick           = onClick
            )
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 15.sp, color = Color(0xFF2A2A2A))
        Icon(
            imageVector        = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint               = Color(0xFFCCCCCC),
            modifier           = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun PlainRow(
    label: String,
    textColor: Color = Color(0xFF2A2A2A),
    onClick: () -> Unit
) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .clickable(
                indication        = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick           = onClick
            )
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 15.sp, color = textColor)
    }
}
