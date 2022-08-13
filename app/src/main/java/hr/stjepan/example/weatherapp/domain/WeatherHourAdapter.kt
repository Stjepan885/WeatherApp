package hr.stjepan.example.weatherapp.domain

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Day
import hr.stjepan.example.weatherapp.data.model.Hour
import java.text.SimpleDateFormat

class WeatherHourAdapter(c: Context, items: List<Hour>): RecyclerView.Adapter<WeatherHourAdapter.ViewHolder>(){

    var items: List<Hour>
    var context: Context

    init {
        this.items = items
        context = c
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val hourTV: TextView
        val tempTV: TextView
        //val cloudsTV: TextView
        val cloudsIV: ImageView

        init {
            hourTV = itemView.findViewById(R.id.textViewItemTime)
            tempTV = itemView.findViewById(R.id.textViewItemClouds)
            cloudsIV = itemView.findViewById(R.id.imageViewItemTemperature)

        }

        override fun onClick(p: View) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_hour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time = holder.hourTV
        val temp = holder.tempTV
        val clouds = holder.cloudsIV

        val simpleDateFormat = SimpleDateFormat("HH")
        val dateString = simpleDateFormat.format(items[position].day * 1000)

        time.text = "$dateString:00"
        temp.text = items[position].temps.temp.toInt().toString() + "°"

        val url = "https://openweathermap.org/img/wn/${items[position].dayWeather[0].icon}@2x.png"
        Picasso.get().load(url).into(clouds)


    }

    override fun getItemCount(): Int {
        return items.size
    }

}