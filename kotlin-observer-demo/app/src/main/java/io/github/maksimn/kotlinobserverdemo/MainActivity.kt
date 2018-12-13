package io.github.maksimn.kotlinobserverdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.properties.Delegates

data class NewMailEventArgs(val from: String, val to: String, val subject: String)

interface NewMailListener {
    fun onNewMail(newMail: NewMailEventArgs)
}

class MailManager {
    private val newMailListeners = mutableListOf<NewMailListener>()

    var newMail: NewMailEventArgs by Delegates.observable(
            NewMailEventArgs("", "", "")
    ) { _, _, newMail->

        Log.i("Mail Manager ", "is emitting a new mail")

        for (listener in newMailListeners) {
            listener.onNewMail(newMail)
        }
    }

    fun addNewMailListener(listener: NewMailListener) {
        newMailListeners.add(listener)
    }

    fun removeNewMailListener(listener: NewMailListener) {
        newMailListeners.remove(listener)
    }
}

class Fax : NewMailListener {
    override fun onNewMail(newMail: NewMailEventArgs) {
        Log.i("Fax received ", newMail.toString())
    }
}

class Pager : NewMailListener {
    override fun onNewMail(newMail: NewMailEventArgs) {
        Log.i("Pager received ", newMail.toString())
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DEMO CODE:
        val mailManager = MailManager()
        val fax = Fax()
        val pager = Pager()

        mailManager.addNewMailListener(fax)
        mailManager.addNewMailListener(pager)

        mailManager.newMail = NewMailEventArgs("Alice", "Bob", "SHUE")

        mailManager.removeNewMailListener(fax)

        mailManager.newMail = NewMailEventArgs("Stalin", "Lenin", "PPSH SHPSH")

        mailManager.removeNewMailListener(pager)

        mailManager.newMail = NewMailEventArgs("A", "B", "C")
    }
}
