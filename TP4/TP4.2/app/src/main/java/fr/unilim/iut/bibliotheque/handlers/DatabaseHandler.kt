package fr.unilim.iut.bibliotheque.handlers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import fr.unilim.iut.bibliotheque.classes.Livre

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,"Bibliotheque",null,1) {
    val table_livre = "Livres"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE $table_livre (" +
                "id INTEGER PRIMARY KEY, " +
                "isbn TEXT," +
                "titre  TEXT" +
                ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $table_livre")
        onCreate(db)
    }

    fun addLivre(livre: Livre):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", livre.id)
        contentValues.put("isbn", livre.isbn) // EmpModelClass Name
        contentValues.put("titre",livre.titre ) // EmpModelClass Phone

        val success = db.insert(table_livre, null, contentValues)

        db.close()
        return success
    }

    fun viewLivres():List<Livre>{
        val empList:ArrayList<Livre> = ArrayList()
        val selectQuery = "SELECT  * FROM $table_livre"
        val db = this.readableDatabase
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var isbn: String
        var titre: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                isbn = cursor.getString(cursor.getColumnIndex("isbn"))
                titre = cursor.getString(cursor.getColumnIndex("titre"))
                val emp= Livre(id, isbn, titre)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return empList
    }


    fun updateLivre(livre: Livre):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", livre.id)
        contentValues.put("isbn", livre.isbn)
        contentValues.put("titre",livre.titre )

        val success = db.update(table_livre, contentValues,"id="+livre.id,null)
        db.close()
        return success
    }

    fun deleteLivre(livre: Livre):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id", livre.id)

        val success = db.delete(table_livre,"id="+livre.id,null)

        db.close()
        return success
    }

}