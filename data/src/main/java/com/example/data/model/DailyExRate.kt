package com.example.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "DailyExRate", strict = false)
data class DailyExRate @JvmOverloads constructor (
        @field:Attribute(name = "Date")
        @param:Attribute(name = "Date")
        var date: String = "",
        @field:ElementList(name = "Currency", inline = true, required = false)
        @param:ElementList(name = "Currency", inline = true, required = false)
        var currencies: List<Currency> = mutableListOf()
)
