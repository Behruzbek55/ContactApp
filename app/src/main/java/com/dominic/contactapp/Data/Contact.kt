package com.dominic.contactapp.Data

class Contact {
    var name:String? = null
    var number:String? = null
    var remove_image:Int? = null
    var call_image:Int? = null
    constructor(name: String?, number: String?, remove_image: Int?,call_image:Int?) {
        this.name = name
        this.number = number
        this.remove_image = remove_image
        this.call_image = call_image
    }
}