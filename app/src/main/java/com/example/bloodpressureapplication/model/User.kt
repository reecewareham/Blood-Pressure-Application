package com.example.bloodpressureapplication.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class User : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var password: String = ""
    var DOB: String = ""
    var timestamp: RealmInstant = RealmInstant.now()
}