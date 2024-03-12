package com.adisastrawan.mysearchsubmission.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.adisastrawan.mysearchsubmission.databinding.ActivityMainBinding
import com.adisastrawan.mysearchsubmission.ui.setting.SettingViewModel
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel : SettingViewModel by viewModels {factory }
        viewModel.getThemeSettings().observe(this){isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}