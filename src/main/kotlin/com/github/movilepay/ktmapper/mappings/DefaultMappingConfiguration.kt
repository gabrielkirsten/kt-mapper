package com.github.movilepay.ktmapper.mappings

import kotlin.reflect.KProperty

class DefaultMappingConfiguration<TSource, TProperty, TTarget>(
    property: KProperty<TProperty>,
    sourceClassName: String
) : MappingConfigurationBase<DefaultMappingConfiguration<TSource, TProperty, TTarget>, TSource, TProperty, TTarget>(
    sourceClassName,
    property
)
