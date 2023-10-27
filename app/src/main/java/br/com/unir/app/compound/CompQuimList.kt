package br.com.unir.app.compound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import br.com.unir.app.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

class CompQuimList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_quim_list)

        val listView = findViewById<ListView>(R.id.listCompQ)

        // Ler os dados do arquivo de bloco de notas e criar objetos Compound
        val compoundList = mutableListOf<Compound>()
        try {
            val inputStream =
                assets.open("CompQ.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            val regex = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))")
            while (reader.readLine().also { line = it } != null) {
                val parts = regex.split(line)
                if (parts.size >= 3) {
                    val name = parts[0].trim()
                    val weight = parts[1].trim()
                    val formula = parts[2].trim()
                    val compound = Compound(name, weight, formula)
                    compoundList.add(compound)
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Preencher a lista com os objetos Compound usando um adaptador personalizado
        val adapter = CompArrayAdapter(this, R.layout.comp_item_layout, compoundList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedCompound = compoundList[position]
            val intent = Intent(this, CompDetailActivity::class.java)
            intent.putExtra("compoundName", selectedCompound.name)
            intent.putExtra("compoundWeight",selectedCompound.weight)
            intent.putExtra("compoundFormula",selectedCompound.formula)
            startActivity(intent)
        }
    }
}