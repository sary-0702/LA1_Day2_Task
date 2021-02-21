package app.nunome.sary.challengeproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener {

            val title: String = titleEditText.text.toString()
            val author: String = autherNameEditText.text.toString()
            val price: String = priceEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title, author, price, content)

            val toListActivityIntent = Intent(this, ListActivity::class.java)
            startActivity(toListActivityIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    fun save(title: String, author: String, price: String, content: String) {
        realm.executeTransaction {
            val book = it.createObject(BookData::class.java)
            book.booktitle = title
            book.auther = author
            book.price = price
            book.contents = content
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toListActivityIntent = Intent(this, ListActivity::class.java)
        startActivity(toListActivityIntent)
        return super.onOptionsItemSelected(item)
    }
}