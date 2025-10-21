package com.umar.laundry.core.ui.utils

import android.view.HapticFeedbackConstants
import android.view.SoundEffectConstants
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Composable
fun (() -> Unit).withClickSound(haptic: Boolean = false): () -> Unit {
    val view = LocalView.current
    return {
        view.playSoundEffect(SoundEffectConstants.CLICK)
        if (haptic) view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        this()
    }
}
