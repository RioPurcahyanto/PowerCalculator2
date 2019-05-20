package com.example.powercalculator

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_specification.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.net.URL

class SpecificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specification)

        referensi_dirjen.onClick {
            val refFiber = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1B4eLM82YC1X4mU-fDyv8rxSwUW0dCFDB/view?usp=sharing"))
            startActivity(refFiber)
        }

        referensi_corning_fiber.onClick {
            val refLossFiber = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1yy3Iu4luo9cI0K_ZPzDTGfUzUAXYEbmM/view?usp=sharing"))
            startActivity(refLossFiber)
        }

        referensi_connector.onClick {
            val refConn = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1SEvE0omz5BR_PMzeshvn4Rp1hSxoo_FW/view?usp=sharing"))
            startActivity(refConn)
        }

        referensi_splicing.onClick {
            val refSplice = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1WOmnkBXMTyUmpkmtrbpV8orDRlsua8M-/view?usp=sharing"))
            startActivity(refSplice)
        }

    }
}
