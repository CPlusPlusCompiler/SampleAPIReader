package lt.andriusdaraskevicius.sampleapireader.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import lt.andriusdaraskevicius.sampleapireader.R

// todo instead of this generic adapter, make a fancier one
class GenericRecyclerAdapter<T>(
    private val items: List<T>
): RecyclerView.Adapter<GenericRecyclerAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            tvText.text = item.toString()
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvText: TextView = view.tvTitle
    }

}