package live.luya.minecollector.serialization

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class GsonDateConverter : JsonSerializer<Date>, JsonDeserializer<Date> {
    companion object {
        private val FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    }

    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return src?.let { JsonPrimitive(FORMAT.format(it)) } ?: JsonNull.INSTANCE
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        return json?.let { FORMAT.parse(it.asString) }
    }
}