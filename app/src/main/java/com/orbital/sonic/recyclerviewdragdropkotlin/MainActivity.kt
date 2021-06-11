package com.orbital.sonic.recyclerviewdragdropkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    lateinit var mExampleList: ArrayList<ExampleItem>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mAdapter: ExampleAdapter

    private lateinit var countryName: Array<String>
    private lateinit var emojiList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryName = resources.getStringArray(R.array.country_name)
        emojiList = resources.getStringArray(R.array.emoji_list)

        createExampleList()
        buildRecyclerView()

    }

    private fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ExampleAdapter(mExampleList)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener(object : ExampleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    mExampleList[position].countryName + ": $position",
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    private fun createExampleList() {
        mExampleList = ArrayList()

        for (index in countryName.indices) {
            mExampleList.add(ExampleItem(countryName[index], emojiList[index]))
        }

    }

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                mAdapter.moveItem(fromPosition, toPosition)
                mAdapter.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                mAdapter.notifyDataSetChanged()
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }

}



