// Copyright 2020 ALO7 Inc. All rights reserved.

package com.alo7.archetype.base.testing

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.MissingNode
import java.math.BigDecimal

/**
 * @author Kelin Tan
 */
class JsonNodeWrapper(val jsonNode: JsonNode) {
    operator fun String.unaryMinus(): JsonNodeWrapper {
        return node
    }

    operator fun String.unaryPlus(): JsonNodeWrapper {
        return node
    }

    val String.node: JsonNodeWrapper
        get() {
            return if (this@JsonNodeWrapper.jsonNode.has(this)) {
                JsonNodeWrapper(this@JsonNodeWrapper.jsonNode.get(this))
            } else {
                JsonNodeWrapper(MissingNode.getInstance())
            }
        }

    val Int.node: JsonNodeWrapper
        get() = item(this)

    operator fun String.invoke(f: JsonNodeWrapper.() -> Unit) {
        val wrapper = node
        wrapper.f()
    }

    operator fun Int.invoke(f: JsonNodeWrapper.() -> Unit) {
        val wrapper = node
        wrapper.f()
    }

    fun item(index: Int, f: JsonNodeWrapper.() -> Unit) {
        return item(index).f()
    }

    fun item(index: Int): JsonNodeWrapper {
        val subNode = jsonNode.get(index)
        return JsonNodeWrapper(subNode)
    }

    fun asText(): String = jsonNode.asText()
    fun asBoolean() = jsonNode.asBoolean()
    fun asInt() = jsonNode.asInt()
    fun asLong() = jsonNode.asLong()
    fun asDouble() = jsonNode.asDouble()
    fun asBigDecimal() = BigDecimal(jsonNode.asText())

    val size
        get() = jsonNode.size()

    val isEmpty
        get() = size == 0

    val isNull
        get() = jsonNode.isNull

    /**
     * Check whether the node has a field.
     */
    val isNone
        get() = jsonNode.isMissingNode

    val isNullOrNone
        get() = jsonNode.isNull || jsonNode.isMissingNode
}