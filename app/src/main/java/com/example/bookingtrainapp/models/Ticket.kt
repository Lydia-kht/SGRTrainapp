package com.example.bookingtrainapp.models

class Ticket {
    var username: String = ""
    var source: String = ""
    var destination: String = ""
    var ticket_number: String = ""
    var record_id: String = ""

    constructor(username:String, source:String, ticket_number:String, destination:String  ){
        this.username = username
        this.source = source
        this.destination = destination
        this.ticket_number = ticket_number
        this.record_id = record_id
    }


}