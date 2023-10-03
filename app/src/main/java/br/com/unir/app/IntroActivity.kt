package br.com.unir.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class IntroActivity : AppCompatActivity() {

    private lateinit var intro: TextView
    private lateinit var ep: TextView
    private lateinit var es: TextView
    private lateinit var et: TextView
    private lateinit var eq: TextView
    private lateinit var mais: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        intro = findViewById(R.id.tvIntro)
        ep = findViewById(R.id.tvEp)
        es = findViewById(R.id.tvEs)
        et = findViewById(R.id.tvEt)
        eq = findViewById(R.id.tvEq)
        mais = findViewById(R.id.btmais)

        intro.setText("As proteínas são como peças-chave na máquina do nosso corpo. Elas têm diferentes formas que são importantes para o seu funcionamento. Aqui estão os quatro tipos de formas das proteínas:")
        ep.setText("Imagine que uma proteína é como um colar de contas. Cada conta é como um bloco de construção chamado aminoácido. A ordem em que essas contas estão ligadas é o que chamamos de estrutura primária.")
        es.setText("As proteínas podem se enrolar ou se dobrar de maneira específica. Isso cria padrões como molas ou dobradiças. Esses padrões são chamados de estrutura secundária. É como fazer um canudo se torcer em uma mola.")
        et.setText("Quando uma proteína está toda dobrada e torcida, temos a estrutura terciária. É como amassar um pedaço de papel em uma forma complicada. A forma importa porque determina como a proteína funciona.")
        eq.setText("Algumas proteínas são feitas de várias partes chamadas subunidades. A maneira como essas partes se encaixam e trabalham juntas é a estrutura quaternária. É como montar um quebra-cabeça com peças diferentes.")

        mais.setOnClickListener{
            val url = "https://brasilescola.uol.com.br/quimica/estruturas-das-proteinas.htm"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

    }
}