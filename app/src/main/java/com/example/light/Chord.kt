package com.example.light

class Chord{
    private var chordName: String? = null

    fun chord(name: String?) {
        this.chordName = name

    }
    fun getChord(): String? {
        return chordName
    }
}

