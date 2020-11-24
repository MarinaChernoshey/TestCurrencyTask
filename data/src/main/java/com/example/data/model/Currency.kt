package com.example.data.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Currency", strict = true)
data class Currency @JvmOverloads constructor (
    @field:Attribute(name = "Id")
    @param:Attribute(name = "Id")
    var currencyId: String = "",

    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    var numCode: String = "",

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    var charCode: String = "",

    @field:Element(name = "Scale")
    @param:Element(name = "Scale")
    var scale: String = "",

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    var name: String = "",

    @field:Element(name = "Rate")
    @param:Element(name = "Rate")
    var rate: String = ""
)