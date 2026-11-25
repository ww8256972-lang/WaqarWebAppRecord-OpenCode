package com.waqar.vault.data.mapper
import com.waqar.vault.database.entity.CustomerEntity
import com.waqar.vault.domain.model.Customer
fun CustomerEntity.toDomain() = Customer(id, serialCode, shopName, personName, phoneE164, whatsappE164, hasWhatsapp, city, category, tags.split(",").filter{it.isNotBlank()}, status, priority, importBatchId)
fun Customer.toEntity() = CustomerEntity(id, serialCode, shopName, personName, phoneE164, whatsappE164, hasWhatsapp, city, category, tags.joinToString(","), status, priority, importBatchId)