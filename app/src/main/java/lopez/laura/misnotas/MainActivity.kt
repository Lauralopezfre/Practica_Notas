package lopez.laura.misnotas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var notas = ArrayList<Nota>()
    lateinit var adaptador: AdaptadorNotas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notasDePrueba()

        var fab: FloatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            var intent = Intent(this, AgregarNotaActivity::class.java)
            startActivityForResult(intent,123)
        }

        adaptador = AdaptadorNotas(this,notas)
        var listview: ListView = findViewById(R.id.listview)
        listview.adapter = adaptador
    }

    fun notasDePrueba(){
        notas.add(Nota("prueba 1", "contenido de la nota 1"))
        notas.add(Nota("prueba 2", "contenido de la nota 2"))
        notas.add(Nota("prueba 3", "contenido de la nota 3"))
    }
}

class AdaptadorNotas: BaseAdapter{

    var context: Context
    var notas = ArrayList<Nota>()

    constructor(context: Context, notas: ArrayList<Nota>){
        this.context = context
        this.notas = notas
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflator = LayoutInflater.from(context)
        var vista = inflator.inflate(R.layout.nota_layout, null)
        var nota = notas[position]

        var titulo: TextView = vista.findViewById(R.id.tv_titulo_det);
        var contenido: TextView = vista.findViewById(R.id.tv_contenido)

        titulo.text = nota.titulo
        titulo.text = nota.contenido
        return vista
    }

    override fun getItem(position: Int): Any {
        return notas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return notas.size
    }

}