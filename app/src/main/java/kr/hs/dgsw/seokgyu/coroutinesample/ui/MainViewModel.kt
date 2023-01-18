package kr.hs.dgsw.seokgyu.coroutinesample.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kr.hs.dgsw.seokgyu.coroutinesample.api.RetrofitClient
import kr.hs.dgsw.seokgyu.coroutinesample.model.UserInfo

class MainViewModel: ViewModel() {
    private val _userInfo = MutableSharedFlow<UserInfo>()
    val userInfo: SharedFlow<UserInfo> get() = _userInfo

    suspend fun getUserInfo(owner: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitClient.githubService()
            .getUserInfo(owner)
            .runCatching {
                _userInfo.emit(this)
            }.onFailure {
                Log.d("error", it.toString())
            }
    }
}