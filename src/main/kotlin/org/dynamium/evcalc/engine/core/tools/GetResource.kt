package org.dynamium.evcalc.engine.core.tools

fun String.asResource() {
    javaClass.getResource(this).readText()
}