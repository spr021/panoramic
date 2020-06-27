package com.example.panoramic.remote

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class NetworkManager {

    fun get(url: String): String {
        return URL(url)
            .openStream()
            .bufferedReader()
            .use {
                it.readText()
            }
    }

    fun post(url: String, body: String): String {
        return URL(url)
            .openConnection()
            .let {
                it as HttpURLConnection
            }.apply {
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
                requestMethod = "POST"

                doOutput = true
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(body)
                outputWriter.flush()
            }.let {
                if (it.responseCode == 200) it.inputStream else it.errorStream
            }.let { streamToRead ->
                BufferedReader(InputStreamReader(streamToRead)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    response.toString()
                }
            }
    }

}