package lt.andriusdaraskevicius.sampleapireader.data

sealed class Resource<out T> {

    data class Success<T>(val data: T): Resource<T>()
    data class Failure<Nothing>(val message: String): Resource<Nothing>()

}