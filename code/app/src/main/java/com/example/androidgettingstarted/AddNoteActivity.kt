package com.example.androidgettingstarted

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*


// image picker code from https://stackoverflow.com/questions/2507898/how-to-pick-an-image-from-gallery-sd-card-for-my-app
// and https://guides.codepath.com/android/Accessing-the-Camera-and-Stored-Media

class AddNoteActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        cancel.setOnClickListener {
            this.finish()
        }

        addNote.setOnClickListener {

            val userData = UserData.shared

            // create a note object
            val note = UserData.Note(
                UUID.randomUUID().toString(),
                name?.text.toString(),
                description?.text.toString()
            )

            // store it in the backend
            Backend.shared.createNote(note)

            // add it to UserData, this will trigger a UI refresh
            userData.addNote(note)

            // close activity
            this.finish()
        }
    }

    companion object {
        private const val TAG = "AddNoteActivity"
        private const val SELECT_PHOTO = 100
    }
}