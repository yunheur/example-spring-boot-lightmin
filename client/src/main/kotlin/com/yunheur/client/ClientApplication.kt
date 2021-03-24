package com.yunheur.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.tuxdevelop.spring.batch.lightmin.client.classic.annotation.EnableLightminClientClassic
import org.tuxdevelop.spring.batch.lightmin.repository.annotation.EnableLightminMapConfigurationRepository

@EnableLightminClientClassic
@EnableLightminMapConfigurationRepository
@SpringBootApplication
class ClientApplication

fun main(args: Array<String>) {
	runApplication<ClientApplication>(*args)
}
