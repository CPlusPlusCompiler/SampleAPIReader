package lt.andriusdaraskevicius.sampleapireader.data

import javax.inject.Inject


class PostsRepository @Inject constructor (
   private val postsService: IPostsService
) {

   suspend fun getPosts() = postsService.getPosts()

}