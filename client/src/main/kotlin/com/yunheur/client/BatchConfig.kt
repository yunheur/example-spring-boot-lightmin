package com.yunheur.client

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class BatchConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {
    @Bean
    fun importUserJob(step: Step): Job =
            jobBuilderFactory.get("importUserJob")
                    .incrementer(RunIdIncrementer())
                    .flow(step)
                    .end()
                    .build()

    @Bean
    fun step(): Step =
            stepBuilderFactory.get("step")
                    .tasklet { contribution, chunkContext ->
                        println("Hello World")
                        RepeatStatus.FINISHED
                    }.build()
}