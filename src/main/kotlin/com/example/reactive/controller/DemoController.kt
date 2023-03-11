package com.example.reactive.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder.getLocale
import org.springframework.context.i18n.LocaleContextHolder.setLocale
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DemoController {

    companion object: KLogging()

    @Autowired
    private val messageSource: ResourceBundleMessageSource? = null

    @GetMapping("/demo")
    suspend fun demo(): String {
        setLocale(Locale.FRENCH)
        return messageSource!!.getMessage("welcome", null, getLocale())
    }

}