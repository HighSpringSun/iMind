package com.kmpstudy.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmpstudy.LocalNavController
import com.kmpstudy.di.di
import com.kmpstudy.viewmodel.SettingsViewModel
import org.kodein.di.instance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage() {
    // 通过依赖注入获取 SettingsViewModel
    val settingsViewModel: SettingsViewModel by di.instance()

    // 创建本地状态来管理输入
    var userNameInput by remember { mutableStateOf(settingsViewModel.userName) }
    var selectedSortOrder by remember { mutableStateOf(settingsViewModel.sortOrder) }
    var selectedTheme by remember { mutableStateOf(settingsViewModel.theme) }
    var selectedLanguage by remember { mutableStateOf(settingsViewModel.language) }
    var autoSaveEnabled by remember { mutableStateOf(settingsViewModel.autoSave) }

    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 用户设置部分
            SettingSection(title = "用户设置") {
                OutlinedTextField(
                    value = userNameInput,
                    onValueChange = { userNameInput = it },
                    label = { Text("用户名") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        settingsViewModel.save(userNameInput)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("保存用户名")
                }
            }

            // 外观设置部分
            SettingSection(title = "外观设置") {
                // 主题选择
                Text(
                    text = "主题",
                    style = MaterialTheme.typography.titleMedium
                )

                val themeOptions =
                    listOf("system" to "跟随系统", "light" to "浅色", "dark" to "深色")
                themeOptions.forEach { (value, label) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedTheme == value,
                            onClick = {
                                selectedTheme = value
                                settingsViewModel.saveTheme(value)
                            }
                        )
                        Text(
                            text = label,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                // 语言选择
                Text(
                    text = "语言",
                    style = MaterialTheme.typography.titleMedium
                )

                val languageOptions = listOf("zh" to "中文", "en" to "English")
                languageOptions.forEach { (value, label) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedLanguage == value,
                            onClick = {
                                selectedLanguage = value
                                settingsViewModel.saveLanguage(value)
                            }
                        )
                        Text(
                            text = label,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            // 功能设置部分
            SettingSection(title = "功能设置") {
                // 排序方式
                Text(
                    text = "排序方式",
                    style = MaterialTheme.typography.titleMedium
                )

                val sortOptions = listOf(
                    "editAt-desc" to "修改时间降序",
                    "editAt-asc" to "修改时间升序",
                    "createAt-desc" to "创建时间降序",
                    "createAt-asc" to "创建时间升序"
                )
                sortOptions.forEach { (value, label) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedSortOrder == value,
                            onClick = {
                                selectedSortOrder = value
                                settingsViewModel.saveSortOrder(value)
                            }
                        )
                        Text(
                            text = label,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                // 自动保存开关
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "自动保存",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Switch(
                        checked = autoSaveEnabled,
                        onCheckedChange = {
                            autoSaveEnabled = it
                            settingsViewModel.saveAutoSave(it)
                        }
                    )
                }
            }

            // 其他操作部分
            SettingSection(title = "其他操作") {
                OutlinedButton(
                    onClick = {
                        settingsViewModel.resetToDefaults()
                        // 重置本地状态
                        userNameInput = "unknown"
                        selectedSortOrder = "editAt-desc"
                        selectedTheme = "system"
                        selectedLanguage = "zh"
                        autoSaveEnabled = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("重置为默认设置")
                }
            }
        }
    }
}

@Composable
private fun SettingSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            content()
        }
    }
}
