package hr.stjepan.example.weatherapp.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import hr.stjepan.example.weatherapp.data.model.WeekWeatherModel
import java.text.SimpleDateFormat

class WeatherDayAdapter(c: Context, items: List<Day>): RecyclerView.Adapter<WeatherDayAdapter.ViewHolder>(){

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
            hourTV = itemView.findViewById(R.id.textViewItemTime)
            tempTV = itemView.findViewById(R.id.textViewCurrentTemperature)
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