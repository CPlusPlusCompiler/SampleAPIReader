package lt.andriusdaraskevicius.sampleapireader.data.entities

data class Post (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    override fun toString(): String {
        return title
    }
}