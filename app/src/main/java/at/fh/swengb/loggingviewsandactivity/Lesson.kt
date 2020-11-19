package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
class Lesson(
    val id: String,
    val name: String,
    val date: String,
    val topic: String,
    val type: LessonType,
    val lecturers: List <Lecturer>,
    val ratings: MutableList <LessonRating>
){
    fun ratingAverage(): Double {
        var sum = 0.0
        for (idx in ratings) {
            sum += idx.ratingValue
        }
        if (ratings.size == 0) return sum
        return sum / ratings.size
    }
}
@JsonClass (generateAdapter = true)
class Lecturer(
    val name:String
)
@JsonClass (generateAdapter = true)
class LessonRating(
    val ratingValue: Double,
    val feedback: String
)

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}