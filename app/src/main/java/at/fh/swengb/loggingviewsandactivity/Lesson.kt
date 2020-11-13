package at.fh.swengb.loggingviewsandactivity

class Lesson(
    val id: String,
    val name: String,
    val date: String,
    val topic: String,
    val type: LessonType,
    val lecturers: List <Lecturer>,
    val lessonRatings: List <LessonRating>
){
    fun ratingAverage(): Double {
        var sum = 0.0
        for (idx in lessonRatings) {
            sum += idx.ratingValue
        }
        return sum / lessonRatings.size
    }
}
class Lecturer(
    val name:String
)

class LessonRating(
    val ratingValue: Int,
    val feedback: String
)

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}