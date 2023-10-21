package com.example.light.practice

import androidx.lifecycle.ViewModel

class PracticeViewModel : ViewModel() {
    private lateinit var practiceModel : PracticeModel

    fun get_user_chord(): String? {
        return practiceModel.get_chord
    }
    fun set_user_chord(chord: String?) {
        if (chord != null) {
            practiceModel.set_chord(chord)
        }
        //notifyPropertyChanged(BR)
    }


}
