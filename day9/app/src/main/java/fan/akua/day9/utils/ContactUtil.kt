package fan.akua.day9.utils

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log

data class Contact(
    val id: String,
    val name: String,
    val phoneNumber: String
)

fun getContacts(contentResolver: ContentResolver): List<Contact> {
    val contacts = mutableListOf<Contact>()

    // 查询联系人数据
    val cursor: Cursor? = contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        ),
        null,
        null,
        null
    )

    // 遍历查询结果
    cursor?.use { c ->
        while (c.moveToNext()) {
            val id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
            val name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            contacts.add(Contact(id, name, phoneNumber))
        }
    }

    // 返回联系人列表
    return contacts
}
