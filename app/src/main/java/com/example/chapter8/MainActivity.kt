package com.example.chapter8

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageButton.setOnClickListener {
            Log.d("loadImageButton", "didtapImageButton")
            checkPermission()
        }

    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("permission", "loadImage")
                loadImage()
            }
            shouldShowRequestPermissionRationale(
                Manifest.permission.READ_MEDIA_IMAGES
            ) -> {
                Log.d("permission", "should alert")
                showPermissionInfoDialog()
            }
            else -> {
                Log.d("permission", "else case")
                requestReadMediaImages()
            }
        }
    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("취소",null)
            setPositiveButton("동의") { _, _ ->
                requestReadMediaImages()
            }
        }.show()
    }

    private fun loadImage() {
        Toast.makeText(this, "이미지를 가져올 예정", Toast.LENGTH_SHORT).show()
    }

    private fun requestReadMediaImages() {
        Log.d("requestReadMediaImages", "requestReadMediaImages")
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQUEST_READ_EXTERNAL_STORAGE)
    }

    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 100
    }
}