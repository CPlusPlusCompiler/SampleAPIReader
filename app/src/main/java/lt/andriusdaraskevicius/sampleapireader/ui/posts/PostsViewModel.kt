package lt.andriusdaraskevicius.sampleapireader.ui.posts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import lt.andriusdaraskevicius.sampleapireader.data.PostsRepository
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (
    private val repository: PostsRepository
): ViewModel() {

    fun getAllPosts() = flow<Resource<List<Post>>> {
        repository.getAllPosts()
            .flowOn(Dispatchers.IO)
            .collect { repoPosts ->
                emit(repoPosts)
            }
    }.flowOn(Dispatchers.IO)


    fun getPost(id: Long) = flow<Resource<Post>> {
        emit(repository.getPost(id))
    }.flowOn(Dispatchers.IO)
}