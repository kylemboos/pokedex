package com.boos.pokedex

import com.squareup.moshi.JsonAdapter
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.buffer
import okio.source

internal inline fun <reified T : Any> JsonAdapter<T>.getModel(source: Any, file: String): T =
    fromJson(source.getBufferedSource(file))
        ?: throw Exception("Could NOT get the model ${T::class.simpleName} from $file")

internal fun Any.getBufferedSource(file: String): BufferedSource {
    val inputStream = this.javaClass.classLoader?.getResourceAsStream(file)
        ?: throw Exception("Cant find that file: $file")
    return inputStream.source().buffer()
}

fun Any.addMockResponse(server: MockWebServer, fileName: String) {
    javaClass.classLoader?.let {
        val inputStream = it.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
}
