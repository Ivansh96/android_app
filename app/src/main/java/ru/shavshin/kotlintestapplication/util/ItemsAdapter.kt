package ru.shavshin.kotlintestapplication.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.shavshin.kotlintestapplication.ItemInfoActivity
import ru.shavshin.kotlintestapplication.R
import ru.shavshin.kotlintestapplication.model.Item

class ItemsAdapter(private var items: List<Item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    class MyViewHolder(private var view: View): RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.item_list_image)
        var title: TextView = view.findViewById(R.id.item_list_title)
        var desc: TextView = view.findViewById(R.id.item_list_desc)
        var price: TextView = view.findViewById(R.id.item_list_price)
        var button: Button = view.findViewById(R.id.item_list_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.desc.text = items[position].desc
        holder.price.text = items[position].price.toString()

        holder.button.setOnClickListener {
            val linkPage = Intent(context, ItemInfoActivity::class.java)
            linkPage.putExtra("itemTitle", items[position].title)
            linkPage.putExtra("itemText", items[position].text)
            context.startActivity(linkPage)
        }

        val imageId = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)
    }
}