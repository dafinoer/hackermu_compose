package com.dafinrs.hackermu.domains.usecase

interface UsecaseMain<T> {

    abstract suspend fun call():T
}