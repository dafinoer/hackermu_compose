import com.dafinrs.hackermu.data.adapter.EventJsonStoryAdapter
import com.dafinrs.hackermu.domains.models.StoryModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.isA
import org.junit.Assert.assertEquals
import org.junit.Test

class EventJsonStoryAdapterTest {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun validate_json_parse() {
        val jsonData = """
            {
                "by": "dhouston",
                "descendants": 71,
                "id": 8863,
                "kids": [],
                "score": 104,
                "time": 1175714200,
                "title": "My YC app: Dropbox - Throw away your USB drive",
                "type": "story",
                "url": "http://www.getdropbox.com/u/2/screencast.html"
            }
        """.trimIndent()

        val moshi =
            Moshi.Builder().add(EventJsonStoryAdapter()).addLast(KotlinJsonAdapterFactory()).build()
        val storyModelAdapter = moshi.adapter<StoryModel>()
        val result = storyModelAdapter.fromJson(jsonData)

        assertThat(result, isA(StoryModel::class.java))
        assertEquals(result?.title, "My YC app: Dropbox - Throw away your USB drive")
    }
}