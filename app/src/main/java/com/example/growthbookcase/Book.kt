package com.example.growthbookcase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val name: String,
    val description: String,
    val image: Int
) : Parcelable