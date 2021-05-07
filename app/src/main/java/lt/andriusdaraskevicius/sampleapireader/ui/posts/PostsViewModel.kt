package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import lt.andriusdaraskevicius.sampleapireader.data.PostsRepository
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.data.models.Post
import lt.andriusdaraskevicius.sampleapireader.data.models.PostWithAuthor
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (
    private val repository: PostsRepository
): ViewModel() {

    var recyclerViewState: Parcelable? = null

    /**
     * @param fullRefresh whether we need fetching form remote data source. It is needed for events like
     * screen rotation, returning back to the posts lists screen, etc.
     * */
    fun getAllPosts(fullRefresh: Boolean) = flow<Resource<List<Post>>> {
        repository.getAllPosts(fullRefresh)
            .flowOn(Dispatchers.IO)
            .collect { repoPosts ->
                emit(repoPosts)
            }
    }.flowOn(Dispatchers.IO)


    fun getPost(id: Long) = flow<Resource<PostWithAuthor>> {
        emit(repository.getPostWithAuthor(id))
    }.flowOn(Dispatchers.IO)
}