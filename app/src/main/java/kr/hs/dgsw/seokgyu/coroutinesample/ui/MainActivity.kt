package kr.hs.dgsw.seokgyu.coroutinesample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.seokgyu.coroutinesample.R
import kr.hs.dgsw.seokgyu.coroutinesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getUserInfo(binding.et.text.toString())
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.userInfo.collect {
                binding.apply {
                    a.text = "name = " + it.userId
                    b.text = "followers = " + it.followers.toString()
                    c.text = "following = " + it.following.toString()
                }
            }
        }
    }
}