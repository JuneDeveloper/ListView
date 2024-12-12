package com.example.listview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val people:MutableList<User> = mutableListOf()

    private lateinit var toolBarMain:Toolbar
    private lateinit var editTextNameET:EditText
    private lateinit var editTextAgeET:EditText
    private lateinit var saveBTN:Button
    private lateinit var listViewLV:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNameET = findViewById(R.id.editTextNameET)
        editTextAgeET = findViewById(R.id.editTextAgeET)
        saveBTN = findViewById(R.id.saveBTN)

        listViewLV = findViewById(R.id.listViewLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, people)
        listViewLV.adapter = adapter

        toolBarMain = findViewById(R.id.toolBarMain)
        setSupportActionBar(toolBarMain)
        title = "Каталог пользователей"

        saveBTN.setOnClickListener {
            if (editTextAgeET.text.isEmpty() || editTextNameET.text.isEmpty()) return@setOnClickListener
            else
                people.add(
                    User(
                        editTextNameET.text.toString(),
                        Integer.parseInt(editTextAgeET.text.toString())
                    )
                )
            adapter.notifyDataSetChanged()
            editTextNameET.text.clear()
            editTextAgeET.text.clear()
        }

        listViewLV.onItemClickListener =
            MyDialog.createDialog(this,adapter)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exitMainMenu -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}