package br.com.unir.app.apidata

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.unir.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var textViewId: TextView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewKeywords: TextView
    private lateinit var textViewDepositDate: TextView
    private lateinit var textViewReleaseDate: TextView
    private lateinit var textViewAuthors: TextView
    private lateinit var webView: WebView

    private val apiService by lazy {
        createApiService()
    }

    private fun createApiService(): DataService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.rcsb.org/rest/v1/core/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DataService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        textViewId = findViewById(R.id.textViewId)
        webView = findViewById(R.id.webView)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewKeywords = findViewById(R.id.textViewKeywords)
        textViewDepositDate = findViewById(R.id.textViewDepositDate)
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate)
        textViewAuthors = findViewById(R.id.textViewAuthors)


        // Obtenha o identifier da Intent
        val identifier = intent.getStringExtra("itemIdentifier")

        // Construa o URL com base no identifier
        val url = "https://chemapps.stolaf.edu/jmol/jmol.php?width=100%&height=100%&pdbid=$identifier"

        // Configure a WebView
        setupWebView()
        webView.loadUrl(url)

        val proteinId = identifier
        if (proteinId != null) {
            searchProtein(proteinId)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true // Ative JavaScript, se necessário

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Opcional: executar JavaScript após o carregamento da página
                // webView.loadUrl("javascript:yourJavaScriptFunction();")
            }
        }
    }
    private fun searchProtein(proteinId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val proteinData = fetchProteinData(proteinId)

            withContext(Dispatchers.Main) {
                if (proteinData != null) {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

                    textViewId.text = "ID: ${proteinData.rcsb_id}"
                    textViewTitle.text = "Título: ${proteinData.struct.title}"
                    textViewKeywords.text = "Palavras-chave: ${proteinData.struct_keywords.text}"
                    textViewDepositDate.text = "Data de Depósito: ${dateFormat.format(proteinData.rcsb_accession_info.deposit_date)}"
                    textViewReleaseDate.text = "Data de Lançamento: ${dateFormat.format(proteinData.rcsb_accession_info.initial_release_date)}"

                    val authors = proteinData.audit_author.joinToString { it.name }
                    textViewAuthors.text = "Autores: $authors"
                } else {
                    showToast("Erro ao buscar dados da proteína.")
                }
            }
        }
    }

    private suspend fun fetchProteinData(proteinId: String): ProteinDataModel? {
        return try {
            val response = apiService.getProteinData(proteinId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun showToast(message: String) {
        // Implemente a exibição de mensagens de erro usando Toast ou outro método.
    }
}
