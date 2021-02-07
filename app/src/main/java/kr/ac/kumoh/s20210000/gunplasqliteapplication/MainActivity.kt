package kr.ac.kumoh.s20210000.gunplasqliteapplication
// 본인의 package 사용할 것

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.kumoh.s20210000.gunplasqliteapplication.databinding.ActivityMainBinding
import kr.ac.kumoh.s20210000.gunplasqliteapplication.databinding.DialogAddBinding

// 본인의 package 사용할 것

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Delegated Property
    private val model: GunplaViewModel by viewModels()
    private lateinit var gunplaAdapter: GunplaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gunplaAdapter = GunplaAdapter(model) { mechanic -> adapterOnClick(mechanic) }
        binding.list.adapter = gunplaAdapter
        binding.list.layoutManager = LinearLayoutManager(applicationContext)

        // import androidx.lifecycle.Observer
        model.getAllMechanic().observe(this, {
            gunplaAdapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener { addItem() }

//        model.insert(Mechanic(0,
//            "건담",
//            "RX-78-2",
//            "Earth Federation",
//            "Luna Titanium",
//            18.0,
//            43.4))
    }

    private fun addItem() {
        val binding = DialogAddBinding.inflate(layoutInflater)
        AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("추가") { _, _ ->
                //val name = binding.name.text.toString()
                //Log.i("Dialog", name)

                if (binding.name.text.toString().isNotEmpty() &&
                    binding.model.text.toString().isNotEmpty())
                    model.insert(Mechanic(
                        0,
                        binding.name.text.toString(),
                        binding.model.text.toString(),
                        binding.manufacturer.text.toString(),
                        binding.armor.text.toString(),
                        binding.height.text.toString().toDouble(),
                        binding.weight.text.toString().toDouble()
                    ))
            }
            .create().show()
    }

    private fun adapterOnClick(mechanic: Mechanic?) {
        if (mechanic == null)
            return
        //Toast.makeText(this, mechanic.model, Toast.LENGTH_SHORT).show()

        val binding = DialogAddBinding.inflate(layoutInflater)
        binding.name.isEnabled = false
        binding.name.setText(mechanic.name)

        binding.model.isEnabled = false
        binding.model.setText(mechanic.model)

        binding.manufacturer.isEnabled = false
        binding.manufacturer.setText(mechanic.manufacturer)

        binding.armor.isEnabled = false
        binding.armor.setText(mechanic.armor)

        binding.height.isEnabled = false
        binding.height.setText(mechanic.height.toString())

        binding.weight.isEnabled = false
        binding.weight.setText(mechanic.weight.toString())

        AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("닫기") { _, _ ->
            }
            .create().show()


    }
}