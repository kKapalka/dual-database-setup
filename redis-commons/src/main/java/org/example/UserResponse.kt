package org.example
import java.io.Serializable

class UserResponse(
    var id: Long,
    var name: String
): Serializable {
    constructor() : this(0, "") {

    }
}