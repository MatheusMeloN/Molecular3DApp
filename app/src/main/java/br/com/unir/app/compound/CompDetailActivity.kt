package br.com.unir.app.compound

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.unir.app.R

class CompDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton3D: RadioButton
    private lateinit var radioButton2D: RadioButton
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_detail)

        webView = findViewById(R.id.webView)
        radioGroup = findViewById(R.id.radioGroup)
        radioButton3D = findViewById(R.id.radioButton3D)
        radioButton2D = findViewById(R.id.radioButton2D)

        // Habilitar JavaScript na WebView (se necessário)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Obter o nome do composto químico a partir dos extras da Intent
        val name = intent.getStringExtra("compoundName")

        // Configurar um listener para o RadioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val is3D = checkedId == R.id.radioButton3D
            loadWebViewContent(name, is3D)
        }

        // Inicialmente, carregar o Modelo 3D (radioButton3D marcado)
        loadWebViewContent(name, true)
    }

    private fun loadWebViewContent(name: String?, is3D: Boolean) {
        val url = if (is3D) {
            "https://chemapps.stolaf.edu/jmol/jmol.php?width=100%&height=100%&model=$name&caption=$name"
        } else {
            "https://cactus.nci.nih.gov/chemical/structure/$name/image?width=400&height=400"
        }

//        val url = "$baseUrl?width=100%&height=100%&model=$name&caption=$name"

        // Carregar a URL na WebView
        webView.loadUrl(url)
    }
}