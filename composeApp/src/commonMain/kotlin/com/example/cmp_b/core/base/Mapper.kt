package com.example.cmp_b.core.base

interface Mapper<I, O> {
    fun mapFrom(from: I): O
}
