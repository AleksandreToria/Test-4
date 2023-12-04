package com.example.test4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainFragmentViewModel : ViewModel() {
    private val _dataFlow = MutableStateFlow<List<Data>>(emptyList())
    val dataFlow: StateFlow<List<Data>> = _dataFlow.asStateFlow()

    fun addValues() {
        viewModelScope.launch {
            val parsedDataList = parseConversationsJson(conversations)

            _dataFlow.value = _dataFlow.value.toMutableList().also {
                it.addAll(parsedDataList)
            }
        }
    }

    private val conversations = "[\n" +
            "   {\n" +
            "      \"id\":1,\n" +
            "      \"image\":\"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg\",\n" +
            "      \"owner\":\"გრიშა ონიანი\",\n" +
            "      \"last_message\":\"თავის ტერიტორიას ბომბავდა\",\n" +
            "      \"last_active\":\"4:20 PM\",\n" +
            "      \"unread_messages\":3,\n" +
            "      \"is_typing\":false,\n" +
            "      \"laste_message_type\":\"text\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":2,\n" +
            "      \"image\":null,\n" +
            "      \"owner\":\"ჯემალ კაკაურიძე\",\n" +
            "      \"last_message\":\"შემოგევლე\",\n" +
            "      \"last_active\":\"3:00 AM\",\n" +
            "      \"unread_messages\":0,\n" +
            "      \"is_typing\":true,\n" +
            "      \"laste_message_type\":\"voice\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":3,\n" +
            "      \"image\":\"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg\",\n" +
            "      \"owner\":\"გურამ ჯინორია\",\n" +
            "      \"last_message\":\"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა\",\n" +
            "      \"last_active\":\"1:00 \",\n" +
            "      \"unread_messages\":0,\n" +
            "      \"is_typing\":false,\n" +
            "      \"laste_message_type\":\"file\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":4,\n" +
            "      \"image\":\"\",\n" +
            "      \"owner\":\"კაკო წენგუაშვილი\",\n" +
            "      \"last_message\":\"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი\",\n" +
            "      \"last_active\":\"1:00 PM\",\n" +
            "      \"unread_messages\":0,\n" +
            "      \"is_typing\":false,\n" +
            "      \"laste_message_type\":\"text\"\n" +
            "   }\n" +
            "]\n"

    private fun parseConversationsJson(jsonString: String): List<Data> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Data::class.java)
        val adapter: JsonAdapter<List<Data>> = moshi.adapter(listType)

        return try {
            adapter.fromJson(jsonString) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}
