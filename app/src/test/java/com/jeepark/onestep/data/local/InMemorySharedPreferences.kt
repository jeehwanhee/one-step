package com.jeepark.onestep.data.local

import android.content.SharedPreferences

/**
 * 테스트용 메모리 기반 SharedPreferences. Android 없이 저장 키와 기본값을 검증하는 데 쓴다.
 * 리스너와 문자열 집합은 지원하지 않는다(쓰는 곳이 없다).
 */
class InMemorySharedPreferences : SharedPreferences {

    private val values = mutableMapOf<String, Any>()

    /** 저장된 키 집합(검증용). */
    val keys: Set<String> get() = values.keys.toSet()

    override fun getAll(): MutableMap<String, *> = values.toMutableMap()

    override fun getString(key: String, defValue: String?): String? = values[key] as? String ?: defValue

    override fun getStringSet(key: String, defValues: MutableSet<String>?): MutableSet<String>? =
        throw UnsupportedOperationException()

    override fun getInt(key: String, defValue: Int): Int = values[key] as? Int ?: defValue

    override fun getLong(key: String, defValue: Long): Long = values[key] as? Long ?: defValue

    override fun getFloat(key: String, defValue: Float): Float = values[key] as? Float ?: defValue

    override fun getBoolean(key: String, defValue: Boolean): Boolean = values[key] as? Boolean ?: defValue

    override fun contains(key: String): Boolean = values.containsKey(key)

    override fun edit(): SharedPreferences.Editor = Editor()

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) =
        throw UnsupportedOperationException()

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) =
        throw UnsupportedOperationException()

    private inner class Editor : SharedPreferences.Editor {
        private val puts = mutableMapOf<String, Any>()
        private val removals = mutableSetOf<String>()
        private var clearAll = false

        override fun putString(key: String, value: String?): SharedPreferences.Editor = also { store(key, value) }
        override fun putStringSet(key: String, values: MutableSet<String>?): SharedPreferences.Editor =
            throw UnsupportedOperationException()
        override fun putInt(key: String, value: Int): SharedPreferences.Editor = also { store(key, value) }
        override fun putLong(key: String, value: Long): SharedPreferences.Editor = also { store(key, value) }
        override fun putFloat(key: String, value: Float): SharedPreferences.Editor = also { store(key, value) }
        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor = also { store(key, value) }

        override fun remove(key: String): SharedPreferences.Editor = also {
            puts.remove(key)
            removals.add(key)
        }

        override fun clear(): SharedPreferences.Editor = also { clearAll = true }

        override fun commit(): Boolean {
            if (clearAll) values.clear()
            removals.forEach { values.remove(it) }
            values.putAll(puts)
            return true
        }

        override fun apply() {
            commit()
        }

        private fun store(key: String, value: Any?) {
            if (value == null) {
                remove(key)
            } else {
                removals.remove(key)
                puts[key] = value
            }
        }
    }
}
