package com.rollercoasterapi

import com.rollercoasterapi.domain.model.Coaster
import com.rollercoasterapi.domain.repository.CoasterRepository
import com.rollercoasterapi.domain.repository.ConfigurationRepository
import com.rollercoasterapi.domain.repository.NumberPropertyDefinitionRepository
import com.rollercoasterapi.domain.repository.StringPropertyDefinitionRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

@Configuration
class InitDb {

    @Bean
    fun init(coasterRepository: CoasterRepository,
             configurationRepository: ConfigurationRepository,
             numberPropertyDefinitionRepository: NumberPropertyDefinitionRepository,
             stringPropertyDefinitionRepository: StringPropertyDefinitionRepository): CommandLineRunner {

        return CommandLineRunner {

            configurationRepository.getAllNumberProperties().forEach {
                numberPropertyDefinitionRepository.add(it)
            }
            configurationRepository.getAllStringProperties().forEach {
                stringPropertyDefinitionRepository.add(it)
            }

            //TODO remove
            coasterRepository.add(Coaster(UUID.randomUUID().toString(), "Silver Star"))
            coasterRepository.add(Coaster(UUID.randomUUID().toString(), "Blue Fire"))
        }
    }
}