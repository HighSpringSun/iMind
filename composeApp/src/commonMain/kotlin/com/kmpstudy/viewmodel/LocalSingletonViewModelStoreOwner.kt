package com.kmpstudy.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


// 单例实现
object LocalSingletonViewModelStoreOwner : ViewModelStoreOwner {
    // 创建一个全局的 ViewModelStore 实例
    private val _viewModelStore = ViewModelStore()

    // 实现 ViewModelStoreOwner 接口的 viewModelStore 属性
    override val viewModelStore: ViewModelStore
        get() = _viewModelStore

//    // 清理资源的方法（可选）
//    fun clear() {
//        _viewModelStore.clear()
//    }
}


@Composable
inline fun <reified VM : ViewModel> singleViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = LocalSingletonViewModelStoreOwner,
    key: String? = null,
    noinline initializer: CreationExtras.() -> VM
): VM =
    viewModel(
        VM::class,
        viewModelStoreOwner,
        key,
        viewModelFactory { initializer(initializer) },
        if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
            viewModelStoreOwner.defaultViewModelCreationExtras
        } else {
            CreationExtras.Empty
        }
    )