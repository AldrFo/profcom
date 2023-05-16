package ru.mpei.profcom.main.model.entities

data class GratitudeDto(
    val id: Int,
    val name: String,
    val semestr: String,
    val dateOfAccept: String,
    val level: String,
    val form: String,
    val dates: String,
    val numberOfDays: String,
    val orderLink: String
)