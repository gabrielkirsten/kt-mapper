package com.github.movilepay.ktmapper.helpers

import com.github.movilepay.ktmapper.reflection.Reflection
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty

@PublishedApi
internal object KtMapperInternalHelper {

    internal fun buildMapConfigRef(sourceClassName: String, property: KProperty<*>): String =
        buildMapConfigRef(
            sourceClassName,
            Reflection.Properties.getDeclaringClass(property).name,
            property.name
        )

    @PublishedApi
    @JvmSynthetic
    internal inline fun <reified TSource, reified TTarget> buildMapConfigRef(param: KParameter): String =
        buildMapConfigRef(
            sourceClassName = TSource::class.java.name,
            targetClassName = TTarget::class.java.name,
            targetElementName = param.name!! // TODO: verificar outra estratégia
        )

    @PublishedApi
    @JvmSynthetic
    internal fun buildMapConfigRef(
        sourceClassName: String,
        targetClassName: String,
        targetElementName: String
    ): String =
        "${sourceClassName}__${targetClassName}_$targetElementName"
}