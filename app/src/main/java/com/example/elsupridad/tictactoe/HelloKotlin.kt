package com.example.elsupridad.tictactoe

import android.support.design.widget.Snackbar
import android.view.View

// Class with constructor
class HelloKotlin (var message: String) {
    // private var text: String = message;
    constructor(): this("Hello World!!!")

    fun makeSnackBar(view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}