package com.example.mobicatestapp.settings

import com.example.mobicatestapp.constants.SettingsConstants.USER_SETTINGS
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings

actual class UserSettingsFactory {
    actual fun createUserSettings(context: Any?): Settings? {
        return AppleSettings.Factory().create(name = USER_SETTINGS)
    }
}