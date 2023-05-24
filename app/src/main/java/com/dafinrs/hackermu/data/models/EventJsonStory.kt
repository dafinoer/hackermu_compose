package com.dafinrs.hackermu.data.models


class EventJsonStory(
    val id: Int,
    val title: String,
    val url: String?,
    val time: Long,
    val descendant: Int = 0
)
