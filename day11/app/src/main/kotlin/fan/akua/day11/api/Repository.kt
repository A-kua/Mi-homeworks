package fan.akua.day11.api

import androidx.lifecycle.LiveData
import fan.akua.day11.bean.StarableBean
import kotlinx.coroutines.flow.SharedFlow

interface Repository {
    var data: SharedFlow<List<StarableBean>>
    fun getData(): List<StarableBean>
    fun starOrUnstarData(id: Int, notify: Boolean)
}