package com.grace.myaftefirebaseproject

class User {
    var name:String = ""
    var email:String = ""
    var id_number:String = ""
    var id:String = ""
    constructor(name:String, email:String, id_number:String, id:String){
        this.name = name
        this.email = email
        this.id_number = id_number
        this.id = id
    }

    constructor(){}
}