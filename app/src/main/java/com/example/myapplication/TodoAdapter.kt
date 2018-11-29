package com.example.myapplication

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView


class TodoAdapter(val items: MutableList<Todo>, val context: Context) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var listener: TodoListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(todo: Todo) {
        items.add(todo)
        notifyItemInserted(items.size)
    }

    fun setClickListener (listener: TodoListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(viewholder: TodoViewHolder, position: Int) {
        var todo: Todo = items.get(position)
        viewholder.textView.text = todo.text
        viewholder.isDone.isChecked = todo.isDone

        viewholder.cardView.setOnClickListener {
            if (listener != null) {
                listener!!.click(todo)
            }
        }

        viewholder.isDone.setOnCheckedChangeListener { buttonView, isChecked ->
            if (listener != null) {
                if(isChecked) listener!!.checkBoxChecked(position)
            }
        }
    }


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.fragmentCardView)
        var isDone: CheckBox = itemView.findViewById(R.id.checkbox)
        var textView: TextView = itemView.findViewById(R.id.todoTextView)
    }

    open interface TodoListener {
        fun click (todo: Todo)
        fun checkBoxChecked(pos: Int)
    }


}