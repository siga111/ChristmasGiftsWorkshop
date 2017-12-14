package lv.luhmirins.christmasgifts.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import lv.luhmirins.christmasgifts.R

class GiftListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Creating adapter with item click callback for recycler view to use.
        val adapter = GiftsListAdapter(this) { giftId ->
            openFormActivity(giftId)
        }

        // Setting recycler view to show items as list
        list_recycler.layoutManager = LinearLayoutManager(this)
        list_recycler.adapter = adapter

        // Fetch all gifts from database and update data in the adapter
        // `getAllGifts()` returns LiveData object that observes specific data in
        // database and calls Observer if anything changes. This way our list is
        // always up to date, even if we change items in form screens.
        ViewModelProviders.of(this).get(GiftListViewModel::class.java)
            .getAllGifts()
            .observe(this, Observer { gifts ->
                gifts?.let { adapter.giftList = gifts }
            })

        list_add.setOnClickListener {
            openFormActivity(null)
        }
    }

    /*
     TODO open form activity
      1) create an `Intent` for `GiftFormActivity`
      2) put id of clicked item into extras (if it not null)
      3) call `startActivity`

      [Cheat 6]
     */
    private fun openFormActivity(giftId: Long?) {
    }
}
