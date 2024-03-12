package com.adisastrawan.mysearchsubmission.ui.setting

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.adisastrawan.mysearchsubmission.databinding.FragmentSettingBinding
import com.adisastrawan.mysearchsubmission.ui.ViewModelFactory


class SettingFragment : Fragment() {
    private var _binding : FragmentSettingBinding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : SettingViewModel by viewModels {factory }
        viewModel.getThemeSettings().observe(viewLifecycleOwner){isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }
        binding.switchTheme.setOnCheckedChangeListener{
            _:CompoundButton?,isChecked:Boolean ->
            viewModel.saveThemeSetting(isChecked)
        }
    }
    fun isDarkTheme(activity: Activity): Boolean = activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

}