package app.myoun.kut.utils

import java.util.Optional

fun <T> Optional<T>.getOrNull(): T? = orElse(null)