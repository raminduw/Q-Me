package com.ramindu.weeraman.data.entities

import com.google.gson.annotations.SerializedName


data class CreateEventRequestDTOTest(
    @SerializedName("user")
    val user: String,
    @SerializedName("name")
    val name: String,
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
    val is_user_name_required: Boolean
)


/*

data class CarFilterDTO(
        @SerializedName("mainTypes")
        val mainTypes: List<MainTypeDTO>?,
        @SerializedName("value")
        val value: String?
)
 */