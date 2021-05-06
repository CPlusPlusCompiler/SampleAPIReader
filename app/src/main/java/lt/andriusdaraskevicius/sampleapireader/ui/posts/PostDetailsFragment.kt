package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.Coil
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.item_post.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.ui.BaseFragment

class PostDetailsFragment : BaseFragment(R.layout.fragment_post_details) {

    private val viewModel by activityViewModels<PostsViewModel>()
    private val args by lazy { PostDetailsFragmentArgs.fromBundle(requireArguments()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {

            // todo get post data together with user's data
            viewModel.getPost(args.postId)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            val post = result.data
                            tvPostTitle.text = post.title
                            tvPostBody.text = post.body
                            tvUserName.text = post.userId.toString()
                        }
                        is Resource.Failure -> {
                            showErrorMessage(result.message)
                        }
                    }
                }
        }
    }
}