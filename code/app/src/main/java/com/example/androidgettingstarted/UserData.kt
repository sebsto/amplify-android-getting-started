package com.example.androidgettingstarted

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// a singleton to hold user data (this is a ViewModel pattern, without inheriting from ViewModel)
class UserData private constructor() {

    companion object {
        private const val TAG = "UserData"
        var shared : UserData = UserData()
            private set
    }

    //
    // observable properties
    //

    // signed in status
    private val _isSignedIn = MutableLiveData<Boolean>(false)
    var isSignedIn: LiveData<Boolean> = _isSignedIn

    fun setSignedIn(newValue : Boolean) {
        // use postvalue() to make the assignation on the main (UI) thread
        _isSignedIn.postValue(newValue)
    }

    // the notes
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())

    // please check https://stackoverflow.com/questions/47941537/notify-observer-when-item-is-added-to-list-of-livedata
    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }
    fun notifyObserver() {
        this._notes.notifyObserver()
    }

    fun notes() : LiveData<MutableList<Note>>  = _notes
    fun addNote(n : Note) {
        val notes = _notes.value
        if (notes != null) {
            notes.add(n)
            _notes.notifyObserver()
        } else {
            Log.e(TAG, "addNote : note collection is null !!")
        }
    }
    fun deleteNote(at: Int) : Note?  {
        val note = _notes.value?.removeAt(at)
        _notes.notifyObserver()
        return note
    }

    fun resetNotes() {
        this._notes.value?.clear()  //used when signing out
        _notes.notifyObserver()
    }


    // a note
    data class Note(val id: String, val name: String, val description: String, var imageName: String? = null) {
        override fun toString(): String = name

        // bitmap image
        var image : Bitmap? = null

    }
}

