package fan.akua.day9.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import fan.akua.day9.R
import fan.akua.day9.databinding.ActivityContactBinding
import fan.akua.day9.databinding.ActivityMainBinding
import fan.akua.day9.utils.getContacts

class ContactActivity : AppCompatActivity() {
    private val contactPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Snackbar.make(binding.root, "授权了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)

        binding.read.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(it, "没授权呢", Snackbar.LENGTH_SHORT).show()
            } else {
                val contentResolver = contentResolver
                val contacts = getContacts(contentResolver)
                val contactsString = contacts.joinToString("\n") { contact ->
                    "ID: ${contact.id}, Name: ${contact.name}, Phone: ${contact.phoneNumber}"
                }
                binding.listStr.text = contactsString
            }
        }
    }
}
