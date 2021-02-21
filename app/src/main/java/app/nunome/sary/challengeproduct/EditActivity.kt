package app.nunome.sary.challengeproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

class EditActivity : AppCompatActivity() {

    var realm : Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val tiTLesBooks = intent.getStringExtra("booKstitle")
        val wrITersBooks = intent.getStringExtra("booKsauther")
        val prICeBooks = intent.getStringExtra("booKsprice")
        val coNTentBooks = intent.getStringExtra("booKscontent")

        tiTlesEditText.setText(tiTLesBooks)
        auTherNameEditText.setText(wrITersBooks)
        prIceEditText.setText(prICeBooks)
        conTentEditText.setText(coNTentBooks)

        add2Button.setOnClickListener {
            val tITle: String = tiTlesEditText.text.toString()
            val aUThor: String = auTherNameEditText.text.toString()
            val pRIce: String = prIceEditText.text.toString()
            val coNTent: String = conTentEditText.text.toString()

            updateRealm(tITle, aUThor, pRIce, coNTent)

            val toListActivityIntent = Intent(this, ListActivity::class.java)
            startActivity(toListActivityIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toListActivityIntent = Intent(this, ListActivity::class.java)
        startActivity(toListActivityIntent)
        return super.onOptionsItemSelected(item)
    }

    fun updateRealm(tITle: String, aUThor: String, pRIce: String, coNTent: String) {
        val tiTLessBooks = intent.getStringExtra("booKstitle")
        val wrITerssBooks = intent.getStringExtra("booKsauther")
        val prICesBooks = intent.getStringExtra("booKsprice")
        val coNTentsBooks = intent.getStringExtra("booKscontent")

        var booKData1 = realm.where(BookData::class.java).equalTo("booktitle", tiTLessBooks).findFirst()
        var booKData2 = realm.where(BookData::class.java).equalTo("auther", wrITerssBooks).findFirst()
        var booKData3 = realm.where(BookData::class.java).equalTo("price", prICesBooks).findFirst()
        var booKData4 = realm.where(BookData::class.java).equalTo("contents", coNTentsBooks).findFirst()

        realm.executeTransaction {
            booKData1?.booktitle = tITle
            booKData2?.auther = aUThor
            booKData3?.price = pRIce
            booKData4?.contents = coNTent
        }
    }
}