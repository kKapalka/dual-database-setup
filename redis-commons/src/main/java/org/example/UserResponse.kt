package org.example
import java.io.Serializable

class UserResponse(
    var id: Long,
    var orgId: Long,
    var name: String
): Serializable {
    constructor() : this(0, 0, "") {

    }
    companion object {
        const val cacheName = "users"

        fun getKeyPattern(): String {
            return "$cacheName::*"
        }
        fun getKeyPatternForOrgId(orgId: Long): String {
            return "$cacheName::orgId=$orgId;id=*"
        }
        fun setKey(result: UserResponse): String {
            return "orgId=${result.orgId};id=${result.id}"
        }
        fun getKeyForOrgIdAndUserId(orgId: Long, id: Long): String {
            return "orgId=$orgId;id=$id"
        }
    }
}