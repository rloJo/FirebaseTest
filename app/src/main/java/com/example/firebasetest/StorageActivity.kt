package com.example.firebasetest

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasetest.databinding.ActivityStorageBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class StorageActivity : AppCompatActivity() {
    lateinit var binding: ActivityStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
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
