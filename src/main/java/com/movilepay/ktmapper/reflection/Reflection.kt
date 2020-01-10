package com.movilepay.ktmapper.reflection

import java.lang.reflect.Member
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

/**
 * Internal class helper for reflection tasks
 */
object Reflection {

    object Clazz {
        inline fun <reified T : Any> getKClass(): KClass<T> =
            T::class

        inline fun <reified T : Any> isDataClass(): Boolean =
            T::class.isData
    }

    object Constructors {
        inline fun <reified T : Any> getAllConstructors(): Collection<KFunction<T>> =
            Clazz.getKClass<T>().constructors

        inline fun <reified T : Any> getConstructorWithMoreParameters(): KFunction<T> =
            Clazz.getKClass<T>().constructors.maxBy { it.parameters.count() }!!

        inline fun <reified T : Any> getConstructorWithLessParameters(): KFunction<T> =
            Clazz.getKClass<T>().constructors.minBy { it.parameters.count() }!!
    }

    object Params {
        fun getFunctionParameters(fn: KFunction<*>): List<KParameter> =
            fn.parameters
    }

    object Properties {
        inline fun <reified T : Any> getPropertyByName(propertyName: String): KProperty<*> {
            val targetProperty: KProperty<*>? =
                Clazz.getKClass<T>().declaredMemberProperties.firstOrNull { it.name == propertyName }
            return targetProperty!!
        }

        inline fun <reified T : Any> getPropertyByIndex(propertyIndex: Int): KProperty<*> =
            Clazz.getKClass<T>().declaredMemberProperties.elementAt(propertyIndex)

        fun <T> getValue(prop: KProperty<*>, src: Any): T =
            prop.call(src) as T

        fun getDeclaringClass(prop: KProperty<*>): Class<*> =
            (prop.javaField as Member? ?: prop.javaGetter)?.declaringClass!!
    }

    object Functions {
        fun <T> invoke(fn: KFunction<T>, params: Collection<Any?>): T =
            fn.call(*params.toTypedArray())
    }

}