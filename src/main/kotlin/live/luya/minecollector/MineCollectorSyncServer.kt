package live.luya.minecollector

import com.doubledeltas.minecollector.MineCollector
import com.doubledeltas.minecollector.data.DataManager
import com.doubledeltas.minecollector.data.GameStatistics
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import live.luya.minecollector.serialization.GsonDateConverter
import java.util.*

import java.util.concurrent.TimeUnit

class MineCollectorSyncServer {
    private val server = embeddedServer(Netty, port = (System.getenv("PORT") ?: "3000").toInt()) {
        install(ContentNegotiation) {
            gson(ContentType.Application.Json) {
                registerTypeAdapter(Date::class.java, GsonDateConverter())
            }
        }

        routing {
            get("/") {
                call.respondText("Hello World!")
            }
            get("/player-data") {
                val data = DataManager.getAllPlayerData()
                    .map {
                        val map = it.toMap()
                        val stat = GameStatistics(it)
                        map["scores"] = stat.toMap()

                        return@map map
                    }
                call.respond(data)
            }
        }
    }

    fun start() {
        MineCollector.getInstance().logger.info("Ktor 서버 시작")
        this.server.start(wait = false)
    }

    fun terminate() {
        this.server.stop(gracePeriod = 5L, timeout = 5L, timeUnit = TimeUnit.SECONDS)
    }
}
