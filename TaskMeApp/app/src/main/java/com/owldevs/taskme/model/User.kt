package com.owldevs.taskme.model
import com.owldevs.taskme.R

data class User (
    val name: String,
    val email: String,
    val password: String,
    var role: String, // "client" or  "tasker",
    val phoneNo: String,
    var userImg: Int = R.drawable.ic_pfp,
    var tasksCompleted: Int? = null, // Nullable for optional tasker-specific properties
    var taskerBio: String? = null,
    var ratingMedia: Double? = null,
    var taskerFounds: Double? = null
)
