package com.example.cmp_b.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDto,
    val phone: String,
    val website: String,
    val company: CompanyDto
)

@Serializable
data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
)

@Serializable
data class GeoDto(
    val lat: String,
    val lng: String
)

@Serializable
data class CompanyDto(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
