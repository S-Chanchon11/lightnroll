package com.example.light.lesson

import com.example.light.R
import com.example.light.chord.ui.ChordFragment
import com.example.light.chord.ui.PdfFragment

class LessonRepository() {

    fun getLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.logo_basic, "Basic", "let's start to learn about guitar with basic lesson."))
        lst.add(LessonModel(R.drawable.logo_inter, "Intermediate", "Strumming, Guitar Scales"))
        lst.add(LessonModel(R.drawable.logo_adv, "Advance", "More than intermediate"))
        lst.add(LessonModel(R.drawable.guitar, "Major Chord", ""))
        lst.add(LessonModel(R.drawable.guitar_head, "Minor Chord", ""))
        lst.add(LessonModel(R.drawable.adv_chord, "Major7 Chord", ""))

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
//        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }

    fun getIntermediateLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.guitar_head, "Strumming", "How to read, Sign Indication"))
        lst.add(LessonModel(R.drawable.guitar_head, "Guitar Scales", "2 essentials patterns"))
        lst.add(LessonModel(R.drawable.note, "Music Sheets", "TAB, Notation"))
//        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }
    fun getAdvancedLessonInfo(): List<LessonModel> {
        val lst: List<LessonModel>
        lst = ArrayList()

        lst.add(LessonModel(R.drawable.guitar_head, "Guitar Scales", "Modes"))
        lst.add(LessonModel(R.drawable.adv_chord, "Advanced Chords", ""))
        lst.add(LessonModel(R.drawable.guitar, "Capo", ""))
        lst.add(LessonModel(R.drawable.sparkle_note, "Song Playing", "Techniques and Components"))
//        lst.add(LessonModel(R.drawable.end_lesson, "End Lesson Test", ""))

        return lst
    }
}
