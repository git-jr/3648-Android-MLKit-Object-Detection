package com.alura.aifound.extensions


fun String.takeTwoWords(): String {
    return this.split(" ").take(2).joinToString(" ").removeSpecialCharacters()
}

fun String.removeSpecialCharacters(): String {
    return this.replace("[^a-zA-Z0-9\\s]".toRegex(), "")
}