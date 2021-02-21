package app.nunome.sary.challengeproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val titlesBooks = intent.getStringExtra("booktitle")
        val writersBooks = intent.getStringExtra("bookauther")
        val priceBooks = intent.getStringExtra("bookprice")
        val contentBooks = intent.getStringExtra("bookcontent")

        titleTextView.setText(titlesBooks)
        authorNameTextView.setText(writersBooks)
        priceTextView.setText(priceBooks)
        contentTextView.setText(contentBooks)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toListActivityIntent = Intent(this, ListActivity::class.java)
        startActivity(toListActivityIntent)

        val titlesBooks = intent.getStringExtra("booktitle")
        val writersBooks = intent.getStringExtra("bookauther")
        val priceBooks = intent.getStringExtra("bookprice")
        val contentBooks = intent.getStringExtra("bookcontent")

        when (item.itemId) {
            R.id.Edit_button -> {
                val toEditIntent = Intent(this@DetailsActivity, EditActivity::class.java)
                toEditIntent.putExtra("booKstitle", titlesBooks)
                toEditIntent.putExtra("booKsauther", writersBooks)
                toEditIntent.putExtra("booKsprice", priceBooks)
                toEditIntent.putExtra("booKscontent", contentBooks)
                startActivity(toEditIntent)
                true
            }
            R.id.delete_button -> {
                val bookData1 = realm.where(BookData::class.java)
                        .equalTo("booktitle", titlesBooks)
                        .findAll()
                val bookData2 = realm.where(BookData::class.java)
                        .equalTo("auther", writersBooks)
                        .findAll()
                val bookData3 = realm.where(BookData::class.java)
                        .equalTo("price", priceBooks)
                        .findAll()
                val bookData4 = realm.where(BookData::class.java)
                        .equalTo("contents", contentBooks)
                        .findAll()

                realm.executeTransaction {
                    bookData1.deleteAllFromRealm()
                    bookData2.deleteAllFromRealm()
                    bookData3.deleteAllFromRealm()
                    bookData4.deleteAllFromRealm()
                }
                startActivity(toListActivityIntent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}