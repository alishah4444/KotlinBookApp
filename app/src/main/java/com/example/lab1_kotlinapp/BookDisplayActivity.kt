package com.example.lab1_kotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class BookDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail_display);


        var intent:Intent = getIntent();
        var bookId = intent.getStringExtra("bookId")

        println(bookId)

        var imgView : ImageView = findViewById(R.id.bookImage);
        var nameView : TextView = findViewById(R.id.name);
        var titleView : TextView = findViewById(R.id.title);
        var countryView : TextView = findViewById(R.id.country);
        var languageView : TextView = findViewById(R.id.language);
        var wikiLinkView : TextView = findViewById(R.id.wikiLink);
        var pageCountView : TextView = findViewById(R.id.pageCount);
        var authorView : TextView = findViewById(R.id.author);
        var publishYearView : TextView = findViewById(R.id.publishYear);




        if(bookId == null){
            bookId = "0";
        }

        val database = FirebaseDatabase.getInstance()
        val bookRef = database.reference.child("Book")
        val query = bookRef.orderByChild("id").equalTo(bookId)


        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val books: MutableList<Book> = mutableListOf()

                for (snapshot in dataSnapshot.children) {
                    val book = snapshot.getValue(Book::class.java)
                    book?.let { books.add(it) }
                }

                if (books.isNotEmpty()) {
                    val book = books[0] // Get the first book from the list

                    authorView.text = book.author
                    titleView.text = book.title
                    countryView.text = book.country
                    languageView.text = book.language
                    pageCountView.text = book.pages?.toString()
                    wikiLinkView.text = book.link
                    publishYearView.text = book.year?.toString()
                    //Image
                    Glide.with(this@BookDisplayActivity)
                        .load(book.imageLink)
                        .into(imgView)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // cancel events
            }
        }

        query.addListenerForSingleValueEvent(valueEventListener)
    }
}