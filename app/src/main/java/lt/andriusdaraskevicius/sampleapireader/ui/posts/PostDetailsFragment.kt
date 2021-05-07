package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.Coil
import coil.load
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.item_post.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.ui.BaseFragment
import lt.andriusdaraskevicius.sampleapireader.util.AppConfig

class PostDetailsFragment : BaseFragment(R.layout.fragment_post_details) {

    private val viewModel by activityViewModels<PostsViewModel>()
    private val args by lazy { PostDetailsFragmentArgs.fromBundle(requireArguments()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindDetails()
    }


    private fun bindDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPost(args.postId)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            tvPostTitle.text = result.data.post.title
                            tvPostBody.text = result.data.post.body
                            tvUserName.text = result.data.user?.name ?: getString(R.string.error_user_null)

                            imgUser.load(AppConfig.BASE_PHOTO_URL + result.data.post.userId) {
                                placeholder(R.drawable.ic_no_photo)
                            }
                        }
                        is Resource.Failure -> {
                            showErrorMessage(result.message) {
                                bindDetails()
                            }
                        }
                    }
                }
        }
    }
}