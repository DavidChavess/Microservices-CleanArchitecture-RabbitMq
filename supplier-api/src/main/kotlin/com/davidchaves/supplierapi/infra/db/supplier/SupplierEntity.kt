package com.davidchaves.supplierapi.infra.db.supplier

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "supplier")
class SupplierEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val name: String,
    val address: String,
    val zipcode: String,
    val state: String
) {}