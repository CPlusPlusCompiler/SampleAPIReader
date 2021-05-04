package lt.andriusdaraskevicius.sampleapireader.data.entities

data class User (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    // todo maybe add company's name
)