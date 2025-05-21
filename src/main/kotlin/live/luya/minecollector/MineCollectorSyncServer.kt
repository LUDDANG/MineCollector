package live.luya.minecollector

import com.doubledeltas.minecollector.MineCollector
import com.doubledeltas.minecollector.data.DataManager
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import java.util.concurrent.TimeUnit

class MineCollectorSyncServer {
    private val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson(ContentType.Application.Json) {}
        }

        routing {
            get("/") {
                call.respondText("Hello World!")
            }
            get("/player-data") {
                val data = DataManager.getAllPlayerData()
                    .map { it.toMap() }
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
