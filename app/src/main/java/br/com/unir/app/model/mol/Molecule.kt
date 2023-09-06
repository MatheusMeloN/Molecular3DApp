package br.com.unir.app.model.mol

data class Molecule(
    val audit_author: List<AuditAuthor>,
    val entry: Entry,
    val rcsb_external_references: List<RcsbExternalReference>,
    val rcsb_id: String,
    val struct: Struct,
    val struct_keywords: StructKeywords
)