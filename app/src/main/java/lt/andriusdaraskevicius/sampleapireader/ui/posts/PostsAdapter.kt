package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_post.view.*
import lt.andriusdaraskevicius.sampleapireader.R
import lt.andriusdaraskevicius.sampleapireader.data.entities.Post

class PostsAdapter(
    private val items: List<Post>,
    val onClick: (item: Post) -> Unit
): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            tvTitle.text = item.toString()
            tvPreview.text = item.body
            container.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvPreview: TextView = view.tvPostPreview
        val container: MaterialCardView = view.mcvContainer
    }

}