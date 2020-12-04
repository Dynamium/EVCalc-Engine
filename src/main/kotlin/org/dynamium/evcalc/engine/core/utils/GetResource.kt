package org.dynamium.evcalc.engine.core.utils

fun String.asResource() {
    javaClass.getResource(this).readText()
}