package com.example.reactive.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.i18n.LocaleContext
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver
import java.util.*


@Configuration
class Internationalization {

    @Bean
    fun acceptHeaderLocaleResolver(): AcceptHeaderLocaleContextResolver? {
        val resolver: AcceptHeaderLocaleContextResolver = AcceptHeaderLocaleContextResolver();
        resolver.defaultLocale = Locale.FRENCH
//        LocaleContextHolder.getLocaleContext()
//        LocaleContextHolder.setLocale(Locale.FRENCH)
        return resolver
    }




    @Bean
    fun messageSource(): ResourceBundleMessageSource? {
        val messageSource: ResourceBundleMessageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("messages")
        return messageSource
    }
}