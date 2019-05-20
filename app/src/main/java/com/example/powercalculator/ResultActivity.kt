package com.example.powercalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var konfigurasiResult:String = ""
    var powerTransmitterResult: Double = 0.0
    var panjangFiberResult: Double = 0.0
    var totalSplicesResult: Double = 0.0
    var totalConnectorResult:Double = 0.0
    var totalSplitter1Result: Double = 0.0
    var totalSplitter2Result: Double = 0.0
    var totalLossFiberResult: Double = 0.0
    var totalLossConnectorResult:Double = 0.0
    var totalLossSplicingResult: Double = 0.0
    var totalLossSplitterResult: Double = 0.0
    var totalLossPureResult: Double = 0.0
    var totalLossResult: Double = 0.0

    val lossFiber = 0.21
    val lossSplices = 0.1
    val lossConnector = 0.75
    val lossSplitter1to2 = 4.2
    val lossSplitter1to4 = 7.6
    val lossSplitter1to8 = 11.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.title = "Result"

        val intent = intent
        konfigurasiResult = intent.getStringExtra("konfigurasiIntent")
        powerTransmitterResult = intent.getDoubleExtra("powerTransmitterIntent", 0.0)
        panjangFiberResult = intent.getDoubleExtra("panjangFiberIntent", 0.0)
        totalSplicesResult = intent.getDoubleExtra("totalSplicesIntent", 0.0)
        totalConnectorResult = intent.getDoubleExtra("totalConnectorIntent",0.0)
        totalSplitter1Result = intent.getDoubleExtra("totalSplitter1Intent", 0.0)
        totalSplitter2Result = intent.getDoubleExtra("totalSplitter2Intent", 0.0)
        totalLossFiberResult = intent.getDoubleExtra("totalLossFiberIntent", 0.0)
        totalLossSplicingResult = intent.getDoubleExtra("totalLossSplicingIntent", 0.0)
        totalLossSplitterResult = intent.getDoubleExtra("totalLossSplitterIntent", 0.0)
        totalLossConnectorResult = intent.getDoubleExtra("totalLossConnectorIntent", 0.0)
        totalLossPureResult = intent.getDoubleExtra("totalLossPureIntent", 0.0)
        totalLossResult = intent.getDoubleExtra("totalLossResultIntent", 0.0)

        tv_konfigurasi.text = konfigurasiResult
        tv_power_transmitter.text = powerTransmitterResult.toString() + " dBm"
        tv_loss_fiber_optik.text = panjangFiberResult.toString() + " km X " + lossFiber.toString() + " dB/km \n Hasil = " + totalLossFiberResult.toString()
        tv_loss_connector.text = totalConnectorResult.toString() + " X " + lossConnector.toString() + " dB/pasang  \n Hasil = " + totalLossConnectorResult.toString()
        tv_loss_splices.text = totalSplicesResult.toString()  + " X " + lossSplices.toString() + " dB/splices \n Hasil = " + totalLossSplicingResult.toString()
        if (konfigurasiResult == "1:4:8"){
            tv_loss_power_splitter.text =
                    "Splitter 1 = 1 to 4 (Loss = "+ lossSplitter1to4.toString() +" dB \n" +
                    "Splitter 2 = 1 to 8 (Loss = "+lossSplitter1to8.toString()+" dB \n" +
                    "Total Loss = " + totalLossSplitterResult.toString() + " dB"
        }
        if (konfigurasiResult == "1:4"){
            tv_loss_power_splitter.text =
                    "Splitter 1 = 1 to 4 (Loss = "+ lossSplitter1to4.toString()+ " dB \n" +
                    "Splitter 2 = Tidak Digunakan \n" +
                    "Total Loss = "+ totalLossSplitterResult.toString() + " dB"
        }
        if (konfigurasiResult == "1:2"){
            tv_loss_power_splitter.text =
                    "Splitter 1 = 1 to 2 (Loss = "+ lossSplitter1to2.toString()+ " dB \n" +
                    "Splitter 2 = Tidak Digunakan \n" +
                    "Total loss = "+ totalLossSplitterResult.toString() + " dB"
        }
        tv_total_loss.text =
                "= Power Transmitter - Total Loss \n" +
                "= Power Transmitter - (Loss Fiber Optik - Loss Connector - Loss Splicing - Loss Splitter) \n"+
                "= "+ powerTransmitterResult.toString() + " - " + totalLossPureResult.toString() + "\n " +
                "= "+ powerTransmitterResult.toString() + " - " + totalLossFiberResult.toString() +" - " + totalLossConnectorResult.toString() + " - " + totalLossSplicingResult.toString() + " - " +
                totalLossSplitterResult.toString() +"\n" +
                "\n Hasil = " + totalLossResult.toString() + " dB"
    }
}
