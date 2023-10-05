package com.playdeadrespawn.keebsitup

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags.Description
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_SPEC = "extra_spec"
        const val EXTRA_LINK = "extra_link"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name:TextView = findViewById(R.id.tv_keebs_name)
        val price:TextView = findViewById(R.id.tv_keebs_price)
        val description:TextView = findViewById(R.id.tv_keebs_description)
        val photo:ImageView = findViewById(R.id.keebs_photo)
        val spec:TextView = findViewById(R.id.tv_keebs_spec)
        val link:TextView = findViewById(R.id.tv_keebs_link)

        val nameKeebs = intent.getStringExtra(EXTRA_NAME)
        val priceKeebs = intent.getStringExtra(EXTRA_PRICE)
        val descriptionKeebs = intent.getStringExtra(EXTRA_DESCRIPTION)
        val specKeebs = intent.getStringExtra(EXTRA_SPEC)
        val linkKeebs = intent.getStringExtra(EXTRA_LINK)
        val photoKeebs = intent.getIntExtra(EXTRA_PHOTO, 0)

        name.text = nameKeebs
        price.text = priceKeebs
        description.text = descriptionKeebs
        spec.text = specKeebs
        link.text = linkKeebs
        photo.setImageResource(photoKeebs)

        val shareButton: Button = findViewById(R.id.btn_share)
        shareButton.setOnClickListener {
            val message = "${name.text}, ${description.text}"

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan dengan:"))
        }
    }
    fun openLink(view: android.view.View) {
        val url = intent.getStringExtra(EXTRA_LINK)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}