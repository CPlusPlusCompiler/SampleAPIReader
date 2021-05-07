package lt.andriusdaraskevicius.sampleapireader.data.remote

import lt.andriusdaraskevicius.sampleapireader.data.models.Post
import lt.andriusdaraskevicius.sampleapireader.data.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface IPostsService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{post_id}")
    suspend fun getPostDetails(@Path("post_id") postId: Long): Post

    @GET("users/{user_id}")
    suspend fun getUserDetails(@Path("user_id") userId: Long): User
}