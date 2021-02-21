package app.nunome.sary.challengeproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_data_cell.view.*

class RecyclerViewAdapter(private val context: Context, private var listener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val books: MutableList<BookData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.book_data_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book= books[position]
        holder.booksTitleTextView.text = book.booktitle
        holder.booksWriterTextView.text = book.auther

        holder.container?.setOnClickListener{
            listener.onItemClick(book)
        }
    }

    fun addAll(books: List<BookData>){
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var booksTitleTextView : TextView = view.findViewById(R.id.booksTextView)
        var booksWriterTextView : TextView = view.findViewById(R.id.bookswriterTextView)

        val container : ConstraintLayout? = view.constraint
    }
    
    interface OnItemClickListener {
        fun onItemClick(item: BookData)
    }
}