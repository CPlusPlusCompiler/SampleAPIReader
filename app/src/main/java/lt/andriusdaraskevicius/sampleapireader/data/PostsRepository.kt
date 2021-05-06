package lt.andriusdaraskevicius.sampleapireader.data

import kotlinx.coroutines.flow.flow
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import lt.andriusdaraskevicius.sampleapireader.data.persistence.PostsDao
import lt.andriusdaraskevicius.sampleapireader.data.persistence.UsersDao
import lt.andriusdaraskevicius.sampleapireader.data.remote.IPostsService
import javax.inject.Inject


class PostsRepository @Inject constructor (
   private val postsService: IPostsService,
   private val postsDao: PostsDao,
   private val usersDao: UsersDao
) {

   val posts = flow<Resource<List<Post>>> {

      val databasePosts = try {
         Resource.Success(postsDao.getAll())
      }
      catch (e: Exception) {
         e.printStackTrace()
         Resource.Failure("System error")
      }

      emit(databasePosts)

      if(databasePosts is Resource.Failure)
         return@flow

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
      }

      emit(remotePosts)
   }
}