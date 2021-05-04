package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post
import lt.andriusdaraskevicius.sampleapireader.ui.BaseFragment

@AndroidEntryPoint
class PostsFragment: BaseFragment(R.layout.fragment_posts) {

    private val viewModel by viewModels<PostsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.posts
                .flowOn(Dispatchers.IO)
                .collect { posts ->
                    rvPosts.adapter = GenericRecyclerAdapter<Post>(posts)
                    rvPosts.layoutManager = LinearLayoutManager(requireContext())
                }
        }
    }

}