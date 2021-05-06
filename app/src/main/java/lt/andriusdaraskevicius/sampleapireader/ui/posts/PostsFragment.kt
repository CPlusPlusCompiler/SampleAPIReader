package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.ui.BaseFragment

@AndroidEntryPoint
class PostsFragment: BaseFragment(R.layout.fragment_posts) {

    // in bigger projects, should use navGraphViewModels and seperate navigation into different graphs.
    private val viewModel by activityViewModels<PostsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllPosts()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            rvPosts.adapter = PostsAdapter(result.data) { clickedPost ->
                                findNavController().navigate(
                                    PostsFragmentDirections.openPostDetails(clickedPost.id)
                                )
                            }
                            rvPosts.layoutManager = LinearLayoutManager(requireContext())
                        }
                        is Resource.Failure -> {
                            showErrorMessage(result.message)
                        }
                    }

                }
        }
    }

}