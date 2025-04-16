package com.example.mvvmarch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(val name: String, val imageRes: Int, val displayName: String): Parcelable
