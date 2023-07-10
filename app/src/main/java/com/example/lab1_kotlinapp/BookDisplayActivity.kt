package com.example.lab1_kotlinapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BookDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail_display);
        var imgView : ImageView = findViewById(R.id.bookImage);
        var nameView : TextView = findViewById(R.id.name);
        var titleView : TextView = findViewById(R.id.title);
        var countryView : TextView = findViewById(R.id.country);
        var languageView : TextView = findViewById(R.id.language);
        var wikiLinkView : TextView = findViewById(R.id.wikiLink);
        var pageCountView : TextView = findViewById(R.id.pageCount);
        var authorView : TextView = findViewById(R.id.author);
        var publishYearView : TextView = findViewById(R.id.publishYear);

        var intent:Intent = getIntent();
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val country = intent.getStringExtra("country")
        val title = intent.getStringExtra("title")
        val publishYear = intent.getStringExtra("publishYear")
        val bookImage = intent.getStringExtra("image")
        val language = intent.getStringExtra("language")
        val pageCount = intent.getStringExtra("pageCount")
        val wikiLink = intent.getStringExtra("wikiLink")

        nameView.text = name;
        authorView.text = author;
        titleView.text = title;
        countryView.text = country;
        languageView.text = language;
        pageCountView.text = pageCount;
        wikiLinkView.text = wikiLink;
        publishYearView.text = publishYear;
//        Glide.with(imgView)
//                .load(bookImage)
//                .into(bookImage)


    }
}