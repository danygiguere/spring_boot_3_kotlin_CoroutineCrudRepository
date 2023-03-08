package com.example.reactive.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
//import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*


@Configuration
public class Internationalization {

//    var resolver: AcceptHeaderLocaleResolver? = null
    var messageSource: ResourceBundleMessageSource? = null
//
//    @Bean
//    fun acceptHeaderLocaleResolver(): AcceptHeaderLocaleResolver? {
//        resolver = AcceptHeaderLocaleResolver()
//        resolver.defaultLocale = Locale.ENGLISH
//        return resolver
//    }

    @Bean
    fun messageSource(): ResourceBundleMessageSource? {
        messageSource = ResourceBundleMessageSource()
        messageSource!!.setBasenames("messages")
        return messageSource
    }
}