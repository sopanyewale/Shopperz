package com.example.mvvmarch.domain

import java.util.Locale

fun String.capitalizeWords(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ENGLISH
        ) else it.toString()
    }
}