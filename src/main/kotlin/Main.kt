import java.lang.IllegalArgumentException


fun main(args: Array<String>) {
   var newStudent = User("Sahaj","2001-01-25")
    println(newStudent)
}


data class User(
    var name: String,
    @AllowedRegex("\\d{4}-\\d{2}-\\d{2}") var dob: String
) {
    init {
        val fields = this::class.java.declaredFields
        fields.forEach { field ->

                if (field.isAnnotationPresent(AllowedRegex::class.java)) {
                    val regex = field.getAnnotation(AllowedRegex::class.java)?.regex
                    if (regex?.toRegex()?.matches(dob) == false) {
                        throw IllegalArgumentException(
                            "Birth date is not " +
                                    "a valid date: $dob"
                        )
                    }
                }



        }


    }
}


@Target(AnnotationTarget.FIELD)
annotation class AllowedRegex(val regex: String)


