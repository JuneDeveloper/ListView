package com.example.listview

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MyDialog {
    companion object{
        fun createDialog(context: Context,adapter: ArrayAdapter<User>) =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Внимание!")
                    .setMessage("Удалить пользователя?")
                    .setCancelable(true)
                    .setNegativeButton("Нет") { dialog, witch ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Да") { dialog, witch ->
                        val some = adapter.getItem(position)
                        adapter.remove(some)
                        Toast.makeText(context, "Пользователь удален", Toast.LENGTH_SHORT).show()
                    }.create()
                builder.show()
            }
    }
}