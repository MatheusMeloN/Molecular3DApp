package br.com.unir.app.apidata

import java.util.Date

data class ProteinDataModel(
    val rcsb_id: String,
    val struct: Struct,
    val struct_keywords: StructKeywords,
    val rcsb_accession_info: AccessionInfo,
    val audit_author: List<AuditAuthor>
)

data class Struct(
    val title: String
)

data class StructKeywords(
    val pdbx_keywords: String,
    val text: String
)

data class AccessionInfo(
    val deposit_date: Date,
    val initial_release_date: Date
)

data class AuditAuthor(
    val name: String
)