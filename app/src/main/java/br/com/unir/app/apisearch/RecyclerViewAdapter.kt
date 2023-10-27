package br.com.unir.app.apisearch

import RetrofitData
import RetrofitData.apiService
import android.annotation.SuppressLint
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val items = mutableListOf<ResultSetItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val apiService = RetrofitData.apiService

        val itemLayout: LinearLayout = itemView.findViewById(R.id.itemLayout)
        val tvIdentifier: TextView = itemView.findViewById(R.id.tvIdentifier)
        val imageViewItem: ImageView = itemView.findViewById(R.id.imageViewItem)
        val tvTittle: TextView = itemView.findViewById(R.id.tvTittle)

        fun bind(item: ResultSetItem) {
            tvIdentifier.text = item.identifier

            val img = item.identifier.lowercase()
            val imageUrl = "https://cdn.rcsb.org/images/structures/"+ img +"_assembly-1.jpeg"
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(imageViewItem)

            GlobalScope.launch(Dispatchers.IO) {
                val titulo = getTittle(item.identifier)

                withContext(Dispatchers.Main) {
                    tvTittle.text = titulo
                }
            }

            itemLayout.setOnClickListener {
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra("itemIdentifier", item.identifier)

                context.startActivity(intent)
            }
        }

    }

    suspend fun getTittle(proteinId: String): String? {
        return try {
            val response = apiService.getProteinData(proteinId)

            if (response.isSuccessful) {
                val proteinData = response.body()
                proteinData?.struct?.title
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<ResultSetItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}