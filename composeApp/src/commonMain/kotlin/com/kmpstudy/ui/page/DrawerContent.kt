package com.kmpstudy.ui.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmpstudy.LocalNavController
import compose.icons.EvaIcons
import compose.icons.TablerIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.PersonDelete
import compose.icons.tablericons.BorderAll
import compose.icons.tablericons.BorderOuter
import compose.icons.tablericons.Power
import compose.icons.tablericons.Search


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawerContent() {
    // 抽屉内容区域，这里可自定义要显示的布局，比如你截图里的用户信息、各类功能入口等
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        val navController = LocalNavController.current
        // 顶部用户栏
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("高先生", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.width(8.dp))
            Text(
                "升级PRO",
                color = Color(0xFFFFA500),
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color(0xFFFFF3E0), RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .clickable {
                        navController.navigate("SettingsPage")
                    }
            )
        }

        Spacer(Modifier.height(24.dp))

        // 统计栏
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("5", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("笔记", color = Color.Gray, fontSize = 12.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("4", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("标签", color = Color.Gray, fontSize = 12.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text("249", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("天", color = Color.Gray, fontSize = 12.sp)
            }
        }

        Spacer(Modifier.height(16.dp))

        // 打卡日历（简化为方格）
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
            ) {
                item(6) {
                    Row {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(10.dp)
                                .padding(1.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(Color(0xFFF5F5F5))
                        )
                    }
                }
            }
            // 右下角绿色方块
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(14.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0xFF4EEA8E))
            )
        }

        // 月份标签
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("5月", color = Color.Gray, fontSize = 12.sp)
            Text("6月", color = Color.Gray, fontSize = 12.sp)
            Text("7月", color = Color.Gray, fontSize = 12.sp)
        }

        // 菜单
        DrawerMenuItem("全部笔记", TablerIcons.BorderAll, Color(0xFF18C964), true)
        DrawerMenuItem("微信输入", TablerIcons.Power)
        DrawerMenuItem("每日回顾", TablerIcons.BorderOuter)
        DrawerMenuItem("找一找", TablerIcons.Search)

        Spacer(Modifier.height(16.dp))

        Text(
            "全部标签",
            color = Color(0xFFBDBDBD),
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        DrawerTagItem("欢迎")
        DrawerTagItem("说是")
        DrawerTagItem("谢谢")

        Spacer(Modifier.height(16.dp))

        DrawerMenuItem("回收站", EvaIcons.Outline.PersonDelete, iconTint = Color(0xFFBDBDBD))
    }
}

@Composable
fun DrawerMenuItem(
    text: String,
    icon: ImageVector,
    bgColor: Color = Color.Transparent,
    selected: Boolean = false,
    iconTint: Color = Color.Black
) {
    val shape = RoundedCornerShape(8.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(if (selected) Color(0xFF18C964) else Color.Transparent, shape)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = if (selected) Color.White else iconTint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text,
            color = if (selected) Color.White else Color.Black,
            fontSize = 16.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
        if (text == "找一找") {
            Spacer(Modifier.width(8.dp))
            Text(
                "AI",
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .background(Color(0xFF18C964), RoundedCornerShape(6.dp))
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            )
        }
    }
}

@Composable
fun DrawerTagItem(tag: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 12.dp)
    ) {
        Text("#", color = Color(0xFFBDBDBD), fontSize = 16.sp)
        Spacer(Modifier.width(8.dp))
        Text(tag, fontSize = 15.sp)
    }
}