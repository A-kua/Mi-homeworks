package fan.akua.day11.repositories

import kotlinx.coroutines.flow.Flow

interface Repository<D> {
    var changeFlow: Flow<Int>
    var data: List<D>
    fun starOrUnstar(id: Int, notify: Boolean)
}