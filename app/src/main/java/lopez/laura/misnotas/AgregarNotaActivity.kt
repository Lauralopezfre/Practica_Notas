package lopez.laura.misnotas

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.jar.Manifest

class AgregarNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        var botonguardar: Button = findViewById(R.id.btn_guardar) as Button
        botonguardar.setOnClickListener {
            guardar_notas()
        }
    }

    fun guardar_notas(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)

        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),23)

        }else{
            guardar()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            235 -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    guardar()
                }else{
                    Toast.makeText(this, "Error: permisos denegados", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun guardar(){
        var et_titulo: TextView = findViewById(R.id.et_titulo) as TextView
        var et_contenido: TextView = findViewById(R.id.et_contenido) as TextView
        var titulo = et_titulo.text.toString()
        var cuerpo = et_contenido.text.toString()

        if(titulo == "" || cuerpo == ""){
            Toast.makeText(this, "Error: campos vacios", Toast.LENGTH_SHORT).show()
        }else{
            try{
                val archivo = File(ubicacion(), titulo + ".txt")
                val fos = FileOutputStream(archivo)
                fos.write(cuerpo.toByteArray())
                fos.close()
                Toast.makeText(
                        this,
                        "Se guardo el archivo en la carpeta publica",
                        Toast.LENGTH_SHORT
                ).show()

            }catch (e: Exception){
                Toast.makeText(this, "Error, no se guardo el archivo", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    private fun ubicacion(): String{
        val carpeta = File(getExternalFilesDir(null), "notas")
        if(!carpeta.exists()) carpeta.mkdir()
        return carpeta.absolutePath
    }


}