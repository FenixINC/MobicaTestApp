package com.example.mobicatestapp.settings

import com.russhwolf.settings.Settings

expect class UserSettingsFactory constructor() {
    fun createUserSettings(context: Any?): Settings?
}