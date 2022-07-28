package hr.stjepan.example.weatherapp.domain

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import java.text.SimpleDateFormat

class WeatherWeekAdapter(c: Context, items: List<Day>): RecyclerView.Adapter<WeatherWeekAdapter.ViewHolder>(){

    var items: List<Day>
    var context: Context

    init {
        this.items = items
        context = c
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val hourTV: TextView
        val tempTV: TextView


        init {
            hourTV = itemView.findViewById(R.id.textViewItemTime1)
            Log.e("Stjepan", "$hourTV")
            tempTV = itemView.findViewById(R.id.textViewItemTemperature1)
        }

        override fun onClick(p: View) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time = holder.hourTV
        val temp = holder.tempTV

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(items[position].day)

        time.text = dateString

        temp.text = items[position].temp.day.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}