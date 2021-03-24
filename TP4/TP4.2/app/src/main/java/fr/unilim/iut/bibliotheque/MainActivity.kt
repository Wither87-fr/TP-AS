package fr.unilim.iut.bibliotheque


import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fr.unilim.iut.bibliotheque.adapters.LivreAdapter
import fr.unilim.iut.bibliotheque.classes.Livre
import fr.unilim.iut.bibliotheque.handlers.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //method for saving records in database
    fun saveRecord(view : View) {
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()
        val databaseHandler = DatabaseHandler(this)
        if(id.trim()!="" && name.trim()!="" && email.trim()!=""){
            val status = databaseHandler.addLivre(EmpModelClass(Integer.parseInt(id),name, email))
            if(status > -1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    fun viewRecord(view : View){
        //creating the instance of DatabaseHandler class
        val databaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<Livre> = databaseHandler.viewLivres()
        val empArrayId = Array(emp.size){"0"}
        val empArrayName = Array(emp.size){"null"}
        val empArrayEmail = Array(emp.size){"null"}
        for((index, e) in emp.withIndex()){
            empArrayId[index] = e.id.toString()
            empArrayName[index] = e.isbn
            empArrayEmail[index] = e.titre
        }
        //creating custom ArrayAdapter
        val myListAdapter = LivreAdapter(this,empArrayId,empArrayName,empArrayEmail)
        listView.adapter = myListAdapter
    }
    //method for updating records based on user id
    fun updateRecord(view : View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val livreID = dialogView.findViewById(R.id.updateId) as EditText
        val livreISBN = dialogView.findViewById(R.id.updateISBN) as EditText
        val livreTitre = dialogView.findViewById(R.id.updateTitre) as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update") { _, _ ->

            val updateId = livreID.text.toString()
            val updateISBN = livreISBN.text.toString()
            val updateTitre = livreTitre.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler = DatabaseHandler(this)
            if (updateId.trim() != "" && updateISBN.trim() != "" && updateTitre.trim() != "") {
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateLivre(
                    Livre(
                        Integer.parseInt(updateId),
                        updateISBN,
                        updateTitre
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record update", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            //pass
        }
        val b = dialogBuilder.create()
        b.show()
    }
    //method for deleting records based on id
    fun deleteRecord(view : View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete") { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler = DatabaseHandler(this)
            if (deleteId.trim() != "") {
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteLivre(
                    Livre(
                        Integer.parseInt(deleteId),
                        "",
                        ""
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            //pass
        }
        val b = dialogBuilder.create()
        b.show()
    }
}