package com.example.androidgettingstarted

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


// https://stackoverflow.com/questions/33985719/android-swipe-to-delete-recyclerview
class SwipeCallback(private val activity: AppCompatActivity): ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT
) {

    private val TAG: String = "SimpleItemTouchCallback"
    private val icon: Drawable? = ContextCompat.getDrawable(
        activity,
        R.drawable.ic_baseline_delete_sweep
    )
    private val background: ColorDrawable = ColorDrawable(Color.RED)

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20
        val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight
        val iconRight: Int = itemView.right - iconMargin
        if (dX < 0) {
            val iconLeft: Int = itemView.right - iconMargin - icon.intrinsicWidth
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            background.setBounds(
                itemView.right + dX.toInt() - backgroundCornerOffset,
                itemView.top, itemView.right, itemView.bottom
            )
            background.draw(c)
            icon.draw(c)
        } else {
            background.setBounds(0, 0, 0, 0)
            background.draw(c)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Toast.makeText(activity, "Moved", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

        Toast.makeText(activity, "deleted", Toast.LENGTH_SHORT).show()

        //Remove swiped item from list and notify the RecyclerView
        Log.d(TAG, "Going to remove ${viewHolder.adapterPosition}")

        val position = viewHolder.adapterPosition
        val userData = UserData.shared

        // remove to note from the list will refresh the UI
        val note = userData.deleteNote(position)

        // async remove from backend
        Backend.shared.deleteNote(note)

    }
}