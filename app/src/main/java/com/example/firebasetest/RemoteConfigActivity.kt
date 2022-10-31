package com.example.firebasetest


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasetest.databinding.ActivityRemoteConfigBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.ktx.storage


class RemoteConfigActivity : AppCompatActivity() {
    lateinit var binding: ActivityRemoteConfigBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoteConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateImg()

        binding.refreshimg.setOnClickListener {
            updateImg()
        }
    }
    private fun updateImg(){
        val rootRef = Firebase.storage.reference
        val remoteConfig = Firebase.remoteConfig

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1
        }

        remoteConfig.setConfigSettingsAsync(settings)

        remoteConfig.fetchAndActivate().addOnSuccessListener {
            val season = remoteConfig.getString("season")
            println("########### $season")
            when (season) {
                "spring" -> {
                    val spring = rootRef.child("Spring.png")
                    spring.getBytes(Long.MAX_VALUE).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val bmp = BitmapFactory.decodeByteArray(it.result, 0, it.result!!.size)
                            binding.imageView.setImageBitmap(bmp)
                        }
                    }
                }
                "summer" -> {
                    val summer = rootRef.child("Summer.png")
                    summer.getBytes(Long.MAX_VALUE).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val bmp = BitmapFactory.decodeByteArray(it.result, 0, it.result!!.size)
                            binding.imageView.setImageBitmap(bmp)
                        }
                    }
                }
                "fall" -> {
                    val fall = rootRef.child("Fall.png")
                    fall.getBytes(Long.MAX_VALUE).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val bmp = BitmapFactory.decodeByteArray(it.result, 0, it.result!!.size)
                            binding.imageView.setImageBitmap(bmp)
                        }
                    }
                }
                "winter" -> {
                    val winter = rootRef.child("Winter.png")
                    winter.getBytes(Long.MAX_VALUE).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val bmp = BitmapFactory.decodeByteArray(it.result, 0, it.result!!.size)
                            binding.imageView.setImageBitmap(bmp)
                        }
                    }
                }
            }

        }
    }
}