package com.playdeadrespawn.keebsitup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Keyboard(
    val name: String,
    val price: String,
    val description: String,
    val specification: String,
    val link: String,
    val photo: Int
): Parcelable
