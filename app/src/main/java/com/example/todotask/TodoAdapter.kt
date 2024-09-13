package com.example.todotask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todotask.databinding.TodoTaskLayoutBinding

class TodoAdapter(
    var dataset: ArrayList<Todo>,
    private val listener: ItemClickListener

) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    class ViewHolder(val binding: TodoTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            TodoTaskLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        val todo = dataset[position]
        viewHolder.binding.tvTodoTaskName.text = todo.name
        viewHolder.binding.tvTodoTaskDetails.text = todo.details
        viewHolder.binding.tvTodoTaskTime.text = todo.time

        viewHolder.itemView.setOnClickListener {
            listener.onItemClick(todo)
        }
        viewHolder.binding.btnDelete.setOnClickListener{
            listener.onItemDelete(todo)
        }
    }


    override fun getItemCount(): Int {
        return dataset.size
    }

    interface ItemClickListener {
        fun onItemClick(todo: Todo)
        fun onItemDelete(todo: Todo)
    }

}



