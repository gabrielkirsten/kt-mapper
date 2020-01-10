package com.movilepay.ktmapper.mappings

import kotlin.reflect.KProperty

class MapForProperty<TTarget, TProperty>(
    @PublishedApi
    @JvmSynthetic
    internal val property: KProperty<TProperty>
) {
    inline fun <reified TSource : Any> whenSourceIs(): DefaultMappingConfiguration<TSource, TProperty, TTarget> =
        DefaultMappingConfiguration(
            property,
            sourceClassName = TSource::class.java.name
        )
}
