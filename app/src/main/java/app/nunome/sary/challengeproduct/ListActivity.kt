package app.nunome.sary.challengeproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val booksdata = readAll()

        val adapter = RecyclerViewAdapter(this, object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(item: BookData) {
                val toDetailsIntent = Intent(this@ListActivity, DetailsActivity::class.java)
                toDetailsIntent.putExtra("booktitle", item.booktitle)
                toDetailsIntent.putExtra("bookauther", item.auther)
                toDetailsIntent.putExtra("bookprice", item.price)
                toDetailsIntent.putExtra("bookcontent", item.contents)
                startActivity(toDetailsIntent)
            }
        })

        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter.addAll(booksdata)

        floatingActionButton.setOnClickListener {
            val toMainIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainIntent)
        }
    }

    fun readAll(): RealmResults<BookData> {
        return realm.where(BookData::class.java).findAll()
    }
}