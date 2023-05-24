package com.dafinrs.hackermu.data.adapter

import com.dafinrs.hackermu.data.models.EventJsonStory
import com.dafinrs.hackermu.domains.models.StoryModel
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

class EventJsonStoryAdapter {

    @FromJson
    fun eventFromJson(eventJson: EventJsonStory): StoryModel = StoryModel(
        id = eventJson.id,
        title = eventJson.title,
        url = eventJson.url,
        descendants = eventJson.descendant,
        time = Date(eventJson.time),
    )

    @ToJson
    fun eventToJson(story: StoryModel): EventJsonStory = EventJsonStory(
        id = story.id,
        title = story.title,
        url = story.url,
        time = story.time.time,
    )
}
