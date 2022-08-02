package hr.stjepan.example.weatherapp.domain

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.stjepan.example.weatherapp.R
import hr.stjepan.example.weatherapp.data.model.Cities

class CityAdapter(c: Context, items: List<Cities>, selectedCityListener: SelectedCityListener): RecyclerView.Adapter<CityAdapter.ViewHolder>(){

    private var items: List<Cities>
    var context: Context
    private val selectedCityListener: SelectedCityListener

    init {
        this.items = items
        context = c
        this.selectedCityListener = selectedCityListener
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val cityTV: TextView

        init {
            cityTV = itemView.findViewById(R.id.city_name)
        }

        override fun onClick(p: View) {
            Log.e("on click", "city")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = holder.cityTV

        city.text = items[position].cityName

        holder.itemView.setOnClickListener{
            selectedCityListener.onClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}