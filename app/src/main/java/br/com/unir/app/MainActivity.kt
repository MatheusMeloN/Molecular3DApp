package br.com.unir.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.unir.app.apisearch.Search
import android.net.Uri
import br.com.unir.app.compound.CompQuimList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intro: Button = findViewById(R.id.btIntro)
        val compq: Button = findViewById(R.id.btCQ)
        val pdb: Button = findViewById(R.id.btPDB)
        val busca: Button = findViewById(R.id.btBusca)

        intro.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        busca.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        pdb.setOnClickListener {
            val url = "https://www.rcsb.org/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        compq.setOnClickListener{
            val intent = Intent(this, CompQuimList::class.java)
            startActivity(intent)
        }
    }
}