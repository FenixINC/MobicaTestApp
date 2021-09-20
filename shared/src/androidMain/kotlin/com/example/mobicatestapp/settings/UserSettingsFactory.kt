package com.example.mobicatestapp.settings

import android.content.Context
import com.example.mobicatestapp.constants.SettingsConstants.USER_SETTINGS
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

actual class UserSettingsFactory {
    actual fun createUserSettings(context: Any?): Settings? {
        return (context as? Context)?.let {
            AndroidSettings.Factory(context = context).create(name = USER_SETTINGS)
        }
    }
}