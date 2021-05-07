package lt.andriusdaraskevicius.sampleapireader.data.models

data class PostWithAuthor (
    val post: Post,
    val user: User?
)