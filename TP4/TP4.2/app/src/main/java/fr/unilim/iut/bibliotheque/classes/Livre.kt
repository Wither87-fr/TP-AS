package fr.unilim.iut.bibliotheque.classes

class Livre (val id : Int, val isbn : String, val titre : String, ) {
    override fun toString(): String {
        return "ID : ${this.id} \nISBN : ${this.isbn} \nTitre : ${this.titre}"
    }
}