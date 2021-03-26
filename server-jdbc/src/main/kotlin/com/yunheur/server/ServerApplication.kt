package com.yunheur.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.tuxdevelop.spring.batch.lightmin.server.annotation.EnableLightminServer
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.annotation.EnableServerSchedulerJdbcRepository

@EnableLightminServer
@EnableServerSchedulerJdbcRepository
@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}
