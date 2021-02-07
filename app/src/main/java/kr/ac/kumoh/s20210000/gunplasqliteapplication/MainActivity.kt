package kr.ac.kumoh.s20210000.gunplasqliteapplication
// 본인의 package 사용할 것

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20210000.gunplasqliteapplication.databinding.ActivityMainBinding
// 본인의 package 사용할 것

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}