package com.example.broadcastrecievers

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var receiver:AirplaneModeChangedReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver= AirplaneModeChangedReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                val intent=Intent(Intent.ACTION_SEND)
                intent.type = "text/plan"
                var sharingBody ="Your body here"
                var shareSubject ="Your Subject here"

                intent.putExtra(Intent.EXTRA_TEXT,sharingBody)
                intent.putExtra(Intent.EXTRA_SUBJECT,shareSubject)

                startActivity(Intent.createChooser(intent,"Share Using "))

            }
        }
        return super.onOptionsItemSelected(item)
    }
}