package com.playdeadrespawn.keebsitup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvKeyboards: RecyclerView
    private val list = ArrayList<Keyboard>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvKeyboards = findViewById(R.id.rv_keebs)
        rvKeyboards.setHasFixedSize(true)
        list.addAll(getListKeyboards())
        showRecyclerList()

    }

    private fun getListKeyboards(): ArrayList<Keyboard> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataSpec = resources.getStringArray(R.array.data_spec)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listKeyboard = ArrayList<Keyboard>()
        for (i in dataName.indices) {
            val keyboard = Keyboard(dataName[i], dataPrice[1], dataDescription[i], dataSpec[i], dataLink[i], dataPhoto.getResourceId(i, -1))
            listKeyboard.add(keyboard)
        }
        return listKeyboard
    }

    private fun showRecyclerList() {
        rvKeyboards.layoutManager = LinearLayoutManager(this)
        val listKeebAdapter = ListKeebAdapter(list)
        rvKeyboards.adapter = listKeebAdapter

        listKeebAdapter.setOnItemCallback(object : ListKeebAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Keyboard) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NAME, data.name)
                intent.putExtra(DetailActivity.EXTRA_PRICE, data.price)
                intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, data.description)
                intent.putExtra(DetailActivity.EXTRA_SPEC, data.specification)
                intent.putExtra(DetailActivity.EXTRA_LINK, data.link)
                intent.putExtra(DetailActivity.EXTRA_PHOTO, data.photo)
                startActivity(intent)
            }
        })
    }
}