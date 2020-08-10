package com.ramindu.weeraman.data.entities

import com.google.gson.annotations.SerializedName

data class CreateEventResponseDTOTest(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("event")
    val event: Event
)

data class Event(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("member_count")
    val member_count: Int,
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("cancel_automatically")
    val cancel_automatically: Boolean,
    @SerializedName("is_unlimited_numbers")
    val is_unlimited_numbers: Boolean,
    @SerializedName("is_user_name_required")
    val is_user_name_required: Boolean,
    @SerializedName("user")
    val user: String
)


