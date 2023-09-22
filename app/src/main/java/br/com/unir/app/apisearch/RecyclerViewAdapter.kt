package br.com.unir.app.apisearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.unir.app.apidata.ItemDetailActivity
import br.com.unir.app.R
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val items = mutableListOf<ResultSetItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLayout: LinearLayout = itemView.findViewById(R.id.itemLayout)
        val textViewIdentifier: TextView = itemView.findViewById(R.id.textViewIdentifier)
        val imageViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)

        fun bind(item: ResultSetItem) {
            textViewIdentifier.text = item.identifier
            // Carregue a imagem da web usando Glide
            val img = item.identifier.lowercase()
            val imageUrl =
                "https://cdn.rcsb.org/images/structures/"+ img +"_assembly-1.jpeg"
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image) // Imagem de espaço reservado
                .into(imageViewItem)

            itemLayout.setOnClickListener {
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra("itemIdentifier", item.identifier)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newItems: List<ResultSetItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
