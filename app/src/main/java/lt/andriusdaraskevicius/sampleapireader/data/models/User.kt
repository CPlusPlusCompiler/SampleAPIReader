package lt.andriusdaraskevicius.sampleapireader.data.models

data class User (
        val id: Long,
        val name: String,
        val username: String,
        val email: String,
        val phone: String,
        val website: String,
)