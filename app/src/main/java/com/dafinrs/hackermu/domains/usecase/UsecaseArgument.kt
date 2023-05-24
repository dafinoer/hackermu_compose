package com.dafinrs.hackermu.domains.usecase

interface UsecaseArgument<T, R> : UsecaseMain<T> {

    suspend fun call(param: R): T
}