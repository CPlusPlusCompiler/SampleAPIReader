package lt.andriusdaraskevicius.sampleapireader.data

import kotlinx.coroutines.flow.flow
import lt.andriusdaraskevicius.sampleapireader.data.models.Post
import lt.andriusdaraskevicius.sampleapireader.data.models.PostWithAuthor
import lt.andriusdaraskevicius.sampleapireader.data.persistence.PostsDao
import lt.andriusdaraskevicius.sampleapireader.data.remote.IPostsService
import javax.inject.Inject


class PostsRepository @Inject constructor (
   private val postsService: IPostsService,
   private val postsDao: PostsDao,
) {

   suspend fun getAllPosts(fullRefresh: Boolean) = flow<Resource<List<Post>>> {
      val databasePosts = try {
         Resource.Success(postsDao.getAll())
      }
      catch (e: Exception) {
         e.printStackTrace()
         Resource.Failure("System error")
      }

      // don't emit twice if we fetch data from an API
      if(!fullRefresh)
         emit(databasePosts)

      if(databasePosts is Resource.Failure)
         return@flow

      // if we don't need fetching data, stop here
      if(!fullRefresh) {
         emit(databasePosts)
         return@flow
      }

      val remotePosts = try {
         Resource.Success(postsService.getPosts())
      }
      catch (e: Exception) {
         e.printStackTrace()
         Resource.Failure("Failed to fetch posts from remote server")
      }

      if(remotePosts is Resource.Success) {
         postsDao.deleteAll()
         postsDao.insertAll(remotePosts.data)
         emit(Resource.Success(postsDao.getAll()))
      }
      else {
         emit (remotePosts)
      }
   }


   suspend fun getPostWithAuthor(id: Long): Resource<PostWithAuthor> {
      val post = postsDao.get(id)

      if(post == null)
        return Resource.Failure("Post not found")

      val user = try {
         postsService.getUserDetails(post.userId)
      }
      catch (e: Exception) {
         e.printStackTrace()
         null
      }

      return Resource.Success(PostWithAuthor(post, user))
   }


}