package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.Resource
import lt.andriusdaraskevicius.sampleapireader.data.models.Post
import lt.andriusdaraskevicius.sampleapireader.ui.BaseFragment

@AndroidEntryPoint
class PostsFragment: BaseFragment(R.layout.fragment_posts) {

    // in bigger projects, should use navGraphViewModels and seperate navigation into different graphs.
    private val viewModel by activityViewModels<PostsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fetchFromRemote = viewModel.recyclerViewState == null
        onRefresh(fetchFromRemote)

        srlPosts.setOnRefreshListener {
            onRefresh(true)
        }
    }


    private fun onRefresh(fullRefresh: Boolean) {
        // in case the refresh wasn't initiated by pulling, we want to show some kind of loading
        if(!srlPosts.isRefreshing)
            srlPosts.isRefreshing = true

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllPosts(fullRefresh)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    srlPosts.isRefreshing = false

                    when(result) {
                        is Resource.Success -> {
                            setupRecyclerView(result.data)
                        }
                        is Resource.Failure -> {
                            showErrorMessage(result.message) {
                                onRefresh(fullRefresh)
                            }
                        }
                    }
                }
        }
    }


    private fun setupRecyclerView(items: List<Post>) {
        rvPosts.apply {
            adapter = PostsAdapter(items) { clickedPost ->
                findNavController().navigate(
                    PostsFragmentDirections.openPostDetails(clickedPost.id))
            }
            layoutManager = LinearLayoutManager(requireContext())

        }

        rvPosts.post {
            viewModel.recyclerViewState?.let { state ->
                rvPosts.layoutManager?.onRestoreInstanceState(state)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.recyclerViewState = rvPosts.layoutManager?.onSaveInstanceState()
    }


}