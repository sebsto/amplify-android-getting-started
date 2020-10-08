package com.example.androidgettingstarted

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.activity_add_note.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
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

            if (this.noteImagePath != null) {
                note.imageName = UUID.randomUUID().toString()
                //note.setImage(this.noteImage)
                note.image = this.noteImage

                // asynchronously store the image (and assume it will work)
                Backend.shared.storeImage(this.noteImagePath!!, note.imageName!!)
            }

            // store it in the backend
            Backend.shared.createNote(note)

            // add it to UserData, this will trigger a UI refresh
            userData.addNote(note)

            // close activity
            this.finish()
        }

        // Set up the listener for add Image button
        captureImage.setOnClickListener {
            val i = Intent(
                Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, SELECT_PHOTO)
        }

        // create rounded corners for the image
        image.shapeAppearanceModel = image.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 150.0f)
            .build()
    }

    private var noteImagePath : String? = null
    private var noteImage : Bitmap? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        Log.d(TAG, "Select photo activity result : $imageReturnedIntent")
        when (requestCode) {
            SELECT_PHOTO -> if (resultCode == RESULT_OK) {
                val selectedImageUri : Uri? = imageReturnedIntent!!.data

                // read the stream to fill in the preview
                var imageStream: InputStream? = contentResolver.openInputStream(selectedImageUri!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val ivPreview: ImageView = findViewById<View>(R.id.image) as ImageView
                ivPreview.setImageBitmap(selectedImage)

                // store the image to not recreate the Bitmap every time
                this.noteImage = selectedImage

                // read the stream to store to a file
                imageStream = contentResolver.openInputStream(selectedImageUri)
                val tempFile = File.createTempFile("image", ".image")
                copyStreamToFile(imageStream!!, tempFile)

                // store the path to create a note
                this.noteImagePath = tempFile.absolutePath

                Log.d(TAG, "Selected image : ${tempFile.absolutePath}")
            }
        }
    }

    private fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
                output.close()
            }
        }
    }

    companion object {
        private const val TAG = "AddNoteActivity"
        private const val SELECT_PHOTO = 100
    }
}