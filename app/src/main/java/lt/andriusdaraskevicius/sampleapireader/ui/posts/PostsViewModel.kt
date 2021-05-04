package lt.andriusdaraskevicius.sampleapireader.ui.posts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import lt.andriusdaraskevicius.sampleapireader.data.PostsRepository
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (
    private val repository: PostsRepository
): ViewModel() {

    val posts = flow<List<Post>> {
        val posts = try {
            repository.getPosts()
        }
        catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }

        emit(posts)
    }
}