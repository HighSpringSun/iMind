# 保留你的 ViewModel、UI 相关类
-keep class com.kmpstudy.ui.viewmodel.** { *; }
-keep class com.kmpstudy.ui.screen.** { *; }

# 保留 Jetpack Compose 相关类
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }

# 保留 SQLDelight 数据库类
-keep class com.kmpstudy.Database.* {*;}

# 保留 Kotlinx Serialization
#-keep @kotlinx.serialization.Serializable class * {}
-keep class kotlinx.serialization.** { *; }