package kr.ac.kumoh.s20210000.gunplasqliteapplication
// package는 본인의 것 사용

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GunplaAdapter(
    private val gunplaModel: GunplaViewModel,
    private val onClick: (Mechanic?) -> Unit
): RecyclerView.Adapter<GunplaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val model: TextView = itemView.findViewById(R.id.model)
        val manufacturer: TextView = itemView.findViewById(R.id.manufacturer)
        //val armor: TextView = itemView.findViewById(R.id.armor)


        init {
            itemView.setOnClickListener {
                onClick(gunplaModel.getMechanic(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // match_parent 적용 안됨
//        val binding = ItemMechanicBinding.inflate(LayoutInflater.from(parent.context))
//        return ViewHolder(binding.root)
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_mechanic,
            parent,
            false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gunplaModel.getMechanic(position)
        holder.name.text = item?.name
        holder.model.text = item?.model
        holder.manufacturer.text = item?.manufacturer
    }

    override fun getItemCount(): Int {
        return gunplaModel.getSize()
    }
}