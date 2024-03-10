package com.adisastrawan.mysearchsubmission.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adisastrawan.mysearchsubmission.ui.setting.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val preferences: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> = preferences.getThemeSetting().asLiveData()
    fun saveThemeSetting(isDarkModeActive : Boolean){
        viewModelScope.launch {
            preferences.saveThemeSetting(isDarkModeActive)
        }
    }
}