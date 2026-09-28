package com.jeepark.onestep.data.repository

/** 테스트용 메모리 기반 VersionStore. 기록된 값의 이력을 남긴다. */
class FakeVersionStore(var version: Long = NO_VERSION) : VersionStore {

    val writes = mutableListOf<Long>()

    override fun read(): Long = version

    override fun write(version: Long) {
        writes.add(version)
        this.version = version
    }
}
