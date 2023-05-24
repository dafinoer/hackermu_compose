package com.dafinrs.hackermu.domains.models

import java.util.Date

data class StoryModel(
    val id: Int,
    val title: String,
    val url: String?,
    val descendants: Int,
    val time: Date,
)
