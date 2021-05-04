package lt.andriusdaraskevicius.sampleapireader.data

import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import lt.andriusdaraskevicius.sampleapireader.data.entities.User
import retrofit2.http.GET
import retrofit2.http.Path

interface IPostsService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{post_id}")
    suspend fun getPostDetails(@Path("post_id") postId: Int): Post

    @GET("users/{user_id}")
    suspend fun getUserDetails(@Path("user_id") userId: Int): User
}