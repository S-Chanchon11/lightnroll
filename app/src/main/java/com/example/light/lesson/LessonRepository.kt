package com.example.light.lesson

import com.example.light.R
import com.example.light.chord.ui.ChordFragment
import com.example.light.chord.ui.PdfFragment

class LessonRepository() {

    fun getLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.logo_basic, "Basic", "sample"))
        lst.add(LessonModel(R.drawable.logo_inter, "Intermediate", "sample"))
        lst.add(LessonModel(R.drawable.logo_adv, "Advance", "sample"))
        lst.add(LessonModel(R.drawable.adv_chord, "Chord Library", "sample"))

        return lst
    }

    fun getSubLessonInfo(level: Int): List<DestinationFragment> {
        val lst: List<DestinationFragment>
        lst = ArrayList()

        when (level) {
            0 -> {
                lst.add(DestinationFragment(PdfFragment(), "BASIC_Intro.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "BASIC_Strumming.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "BASIC_SCALING.pdf"))
                lst.add(DestinationFragment(ChordFragment(), "Major"))
            }
            1 -> {
                lst.add(DestinationFragment(PdfFragment(), "INT_Strumming.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "INT_Scaling.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "INT_Music Sheet.pdf"))
                lst.add(DestinationFragment(ChordFragment(), "Minor"))
            }
            2 -> {
                lst.add(DestinationFragment(PdfFragment(), "ADV_Scaling.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "ADV_AdvancedChord.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "ADV_Capo.pdf"))
                lst.add(DestinationFragment(PdfFragment(), "ADV_SongPlaying.pdf"))
                lst.add(DestinationFragment(ChordFragment(), "Maj7"))
            }
        }

        return lst
    }
    fun getBasicLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.duotune, "Introduction", "Guitar Anatomy, Chord Diagram"))
        lst.add(LessonModel(R.drawable.guitar_head, "Strumming", "How to read, Sign Indication"))
        lst.add(LessonModel(R.drawable.guitar_head, "Guitar Scales", "2 essentials patterns"))
        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }

    fun getIntermediateLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.guitar_head, "Strumming", "How to read, Sign Indication"))
        lst.add(LessonModel(R.drawable.guitar_head, "Guitar Scales", "2 essentials patterns"))
        lst.add(LessonModel(R.drawable.note, "Music Sheets", "TAB, Notation"))
        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }
    fun getAdvancedLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.guitar_head, "Guitar Scales", "Modes"))
        lst.add(LessonModel(R.drawable.adv_chord, "Advanced Chords", ""))
        lst.add(LessonModel(R.drawable.guitar, "Capo", "Desc_C"))
        lst.add(LessonModel(R.drawable.sparkle_note, "Song Playing", "Techniques and Components"))
        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }
}
