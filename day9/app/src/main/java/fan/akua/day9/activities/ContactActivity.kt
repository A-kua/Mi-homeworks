package fan.akua.day9.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import fan.akua.day9.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private val groupPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val readContactsGranted = permissions[Manifest.permission.READ_CONTACTS] ?: false
            val writeContactsGranted = permissions[Manifest.permission.WRITE_CONTACTS] ?: false

            if (readContactsGranted && writeContactsGranted) {
                Snackbar.make(binding.root, "授权了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private val signalPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Snackbar.make(binding.root, "授权了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private lateinit var binding: ActivityContactBinding
    private val contactsPermissions = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupPermissionLauncher.launch(contactsPermissions)

        binding.read.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(binding.root, "我权限呢？", Snackbar.LENGTH_SHORT).show()
            } else {
                readContact()
            }
        }
        binding.call.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                signalPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            } else {
                makeCall(binding.number.text.toString())
            }

        }
    }

    private fun readContact() {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val nameColStr = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        val numberColStr = ContactsContract.CommonDataKinds.Phone.NUMBER
        contentResolver.query(uri, null, null, null, null)
            ?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex(nameColStr))
                    val number = getString(getColumnIndex(numberColStr))
                    binding.listStr.text = binding.listStr.text.toString() + "\n$name $number"
                }
                close()
            }
    }

    private fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }
}
