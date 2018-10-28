package com.example.elsupridad.tictactoe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TextView
import android.widget.TableRow

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
class MainActivity : AppCompatActivity() {
    private var turn = 'X'
    private var gameBoard: Array<Array<Char>> = arrayOf(arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        var turntext = findViewById<TextView>(R.id.turnTextView)
        var table = findViewById<TableLayout>(R.id.tablecontainer)

        startGame(true);

        fab.setOnClickListener { view ->
            HelloKotlin("Fuck the world").makeSnackBar(view)
        }
    }

    private  fun startGame(setClickListener: Boolean) {
        turn = 'X'
        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)
        for (i in 0 until gameBoard.size) {
            for (j in 0 until gameBoard[i].size) {
                gameBoard[i][j] = ' ';
                var cell = (tablecontainer.getChildAt(i) as TableLayout).getChildAt(j) as TextView
                if (setClickListener) {
                    cell.setOnClickListener {view ->
                        cellClickListener(i, j)
                    }
                }
            }
        }
    }

    private fun cellClickListener(row: Int, column: Int) {
        gameBoard[row][column] = turn
        ((tablecontainer?.getChildAt(row) as TableRow).getChildAt(column) as TextView).text =
                turn.toString()
        turn = if ('X' == turn) 'O' else 'X'
        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
