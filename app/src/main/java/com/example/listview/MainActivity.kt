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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: ArrayAdapter<User>

    private lateinit var toolBarMain:Toolbar
    private lateinit var editTextNameET:EditText
    private lateinit var editTextAgeET:EditText
    private lateinit var saveBTN:Button
    private lateinit var listViewLV:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBarMain = findViewById(R.id.toolBarMain)
        setSupportActionBar(toolBarMain)
        title = "Каталог пользователей"

        editTextNameET = findViewById(R.id.editTextNameET)
        editTextAgeET = findViewById(R.id.editTextAgeET)
        saveBTN = findViewById(R.id.saveBTN)
        listViewLV = findViewById(R.id.listViewLV)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewLV.adapter = adapter

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.userList.observe(this){ updateList ->
            adapter.clear()
            adapter.addAll(updateList)
            adapter.notifyDataSetChanged()
        }

        saveBTN.setOnClickListener {
            val name = editTextNameET.text.toString()
            val ageText = editTextAgeET.text.toString()
            if (name.isNotEmpty() && ageText.isNotEmpty()) {
                val age = ageText.toInt()
                val currentList = userViewModel.userList.value?: mutableListOf()
                currentList.add(User(name,age))
                userViewModel.userList.value = currentList
                editTextNameET.text.clear()
                editTextAgeET.text.clear()
            }
            else return@setOnClickListener
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


