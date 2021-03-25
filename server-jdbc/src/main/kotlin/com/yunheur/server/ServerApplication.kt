package com.yunheur.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.tuxdevelop.spring.batch.lightmin.server.annotation.EnableLightminServer

@EnableLightminServer
@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}
