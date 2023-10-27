package br.com.unir.app.compound

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.unir.app.R
import com.squareup.picasso.Picasso

class CompArrayAdapter(
    context: Context,
    resource: Int,
    objects: List<Compound>
) : ArrayAdapter<Compound>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val compound = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.comp_item_layout, parent, false)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewWeight = view.findViewById<TextView>(R.id.textViewWeight)
        val textViewFormula = view.findViewById<TextView>(R.id.textViewFormula)

        textViewName.text = compound?.name
        textViewWeight.text = "Peso: ${compound?.weight}"
        textViewFormula.text = "Fórmula: ${compound?.formula}"

        return view
    }
}
