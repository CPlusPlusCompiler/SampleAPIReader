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

    val posts = flow<Resource<List<Post>>> {
        repository.posts
            .flowOn(Dispatchers.IO)
            .collect { repoPosts ->
                emit(repoPosts)
            }

    }.flowOn(Dispatchers.IO)
}