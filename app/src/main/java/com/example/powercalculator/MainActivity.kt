package com.example.powercalculator


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.powercalculator.R.array.konfigurasi_list
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    var konfigurasiNetwork: String = ""
    var powerTransmitter: String = ""
    var panjangFiber: String = ""
    var totalSplices: String = ""
    var totalConnector: String = ""
    var powerTransmitterDouble: Double = 0.0
    var panjangFiberDouble: Double = 0.0
    var totalSplicesDouble: Double = 0.0
    var totalConnectorDouble: Double = 0.0
    var splitter1: Double = 0.0
    var splitter2: Double = 0.0

    var code1:Int=0
    var code2:Int=0
    var code3 = 0
    var code4 = 0

    var totalLossSplitter1: Double = 0.0
    var totalLossSplitter2: Double = 0.0

    val lossFiber = 0.21
    val lossSplices = 0.1
    val lossConnector = 0.75
    val lossSplitter1to2 = 4.2
    val lossSplitter1to4 = 7.6
    val lossSplitter1to8 = 11.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerItems = resources.getStringArray(konfigurasi_list)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_konfig.adapter = spinnerAdapter

        spinner_konfig.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                konfigurasiNetwork = spinner_konfig.selectedItem.toString()
                when(konfigurasiNetwork){
                    "1:4:8" -> tv_power_splitter.text = "1 to 4 dan 1 to 8"
                    "1:4" -> tv_power_splitter.text = "1 to 4"
                    "1:2" -> tv_power_splitter.text = "1 to 2"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinner_konfig.prompt = "Pilih Konfigurasi"
            }
        }

        btn_hitung.onClick {
            if(konfigurasiNetwork.equals("1:4:8")){
                splitter1 = 1.0
                splitter2 = 1.0
                totalLossSplitter1 = splitter1.times(lossSplitter1to4)
                totalLossSplitter2 = splitter2.times(lossSplitter1to8)
            }
            if (konfigurasiNetwork.equals("1:4")){
                splitter1 = 1.0
                splitter2 = 0.0
                totalLossSplitter1 = splitter1.times(lossSplitter1to4)
                totalLossSplitter2 = splitter2
            }
            if(konfigurasiNetwork.equals("1:2")){
                splitter1 = 1.0
                splitter2 = 0.0
                totalLossSplitter1 = splitter1.times(lossSplitter1to2)
                totalLossSplitter2 = splitter2
            }
            powerTransmitter = edit_powerTransmitter.text.toString()
            panjangFiber = edit_panjang.text.toString()
            totalConnector = edit_connector.text.toString()
            totalSplices = edit_splicing.text.toString()


            if (powerTransmitter.trim().length>0){
                powerTransmitterDouble = powerTransmitter.toDouble()
                code1 = 1
            } else {
                code1 = 0
                longToast("Masukkan Nominal Power Transmitter").show()
            }
            if (panjangFiber.trim().length>0){
                code2 = 1
                panjangFiberDouble = panjangFiber.toDouble()
            } else {
                code2 = 0
                longToast("Masukkan Nominal Panjang Kabel").show()
            }
            if (totalConnector.trim().length>0){
                code3 = 1
                totalConnectorDouble = totalConnector.toDouble()
            } else {
                code3 = 0
                longToast("Masukkan Jumlah Connector").show()
            }
            if (totalSplices.trim().length>0){
                code4 = 1
                totalSplicesDouble = totalSplices.toDouble()
            } else {
                code4 = 0
                longToast("Masukkan Jumlah Splices").show()
            }


            var totalLossFiber:Double = panjangFiberDouble.times(lossFiber)
            var totalLossConnector: Double = totalConnectorDouble.times(lossConnector)
            var totalLossSplicing: Double = totalSplicesDouble.times(lossSplices)
            var totalLossSplitter:Double = totalLossSplitter1.plus(totalLossSplitter2)
            var totalLossPure:Double = totalLossConnector.plus(totalLossFiber).plus(totalLossSplitter).plus(totalLossSplicing)
            var totalLossResult: Double = powerTransmitterDouble.minus(totalLossPure)

            if (code1 == 1 && code2 == 1 && code3 == 1 && code4 == 1){
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("konfigurasiIntent",konfigurasiNetwork)
                intent.putExtra("powerTransmitterIntent", powerTransmitterDouble)
                intent.putExtra("panjangFiberIntent", panjangFiberDouble)
                intent.putExtra("totalSplicesIntent", totalSplicesDouble)
                intent.putExtra("totalConnectorIntent", totalConnectorDouble)
                intent.putExtra("totalSplitter1Intent", splitter1)
                intent.putExtra("totalSplitter2Intent", splitter2)
                intent.putExtra("totalLossFiberIntent", totalLossFiber)
                intent.putExtra("totalLossConnectorIntent", totalLossConnector)
                intent.putExtra("totalLossSplicingIntent", totalLossSplicing)
                intent.putExtra("totalLossSplitterIntent", totalLossSplitter)
                intent.putExtra("totalLossPureIntent", totalLossPure)
                intent.putExtra("totalLossResultIntent", totalLossResult)
                startActivity(intent)
            } else{
                snackbar(findViewById(R.id.edit_connector),"Harap Periksa Kembali")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.menu_attachment -> {
            val spesifikasiIntent = Intent(this, SpecificationActivity::class.java)
            startActivity(spesifikasiIntent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


}
