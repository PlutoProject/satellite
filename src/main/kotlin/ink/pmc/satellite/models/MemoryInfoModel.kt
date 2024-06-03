package ink.pmc.satellite.models

import kotlinx.serialization.Serializable

@Serializable
data class MemoryInfoModel(
    val totalHeapMemory: Long,
    val freeHeapMemory: Long,
    val usedHeapMemory: Long
)