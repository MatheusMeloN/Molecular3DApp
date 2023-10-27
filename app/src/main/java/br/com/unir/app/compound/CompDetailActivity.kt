package br.com.unir.app.compound

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.unir.app.R
import com.squareup.picasso.Picasso

class CompDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var twName: TextView
    private lateinit var twWeight: TextView
    private lateinit var twFormula: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton3D: RadioButton
    private lateinit var radioButton2D: RadioButton
    private lateinit var imageView2D: ImageView
    @SuppressLint("SetJavaScriptEnabled", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_detail)

        twName = findViewById(R.id.twTittle)
        twWeight = findViewById(R.id.twWeight)
        twFormula = findViewById(R.id.twFormula)
        webView = findViewById(R.id.webView)
        radioGroup = findViewById(R.id.radioGroup)
        radioButton3D = findViewById(R.id.radioButton3D)
        radioButton2D = findViewById(R.id.radioButton2D)
        imageView2D = findViewById(R.id.imageView2D)

        // Habilitar JavaScript na WebView (se necessário)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Obter o nome do composto químico a partir dos extras da Intent
        val name = intent.getStringExtra("compoundName")
        val weight = "Peso: "+intent.getStringExtra("compoundWeight")
        val formula = "Fórmula: "+intent.getStringExtra("compoundFormula")

        twName.text = name
        twWeight.text = weight
        twFormula.text = formula

        // Configurar um listener para o RadioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioButton3D) {
                loadWebViewContent(name, true)
                webView.visibility = android.view.View.VISIBLE
                imageView2D.visibility = android.view.View.GONE
            } else if (checkedId == R.id.radioButton2D) {
                loadImageViewContent(name)
                webView.visibility = android.view.View.GONE
                imageView2D.visibility = android.view.View.VISIBLE
            }
        }

        // Inicialmente, carregar o Modelo 3D (radioButton3D marcado)
        loadWebViewContent(name, true)
    }

    private fun loadWebViewContent(name: String?, is3D: Boolean) {
        val url = if (is3D) {
            "https://chemapps.stolaf.edu/jmol/jmol.php?width=100%&height=100%&model=$name"
        } else {
            "https://cactus.nci.nih.gov/chemical/structure/$name/image?width=400&height=400"
        }
        webView.loadUrl(url)
    }

    private fun loadImageViewContent(name: String?) {
        // Construir o URL da imagem 2D
        val url = "https://cactus.nci.nih.gov/chemical/structure/$name/image?/image?width=300&height=300"

        Picasso.get()
            .load(url)
            .into(imageView2D)
    }
}