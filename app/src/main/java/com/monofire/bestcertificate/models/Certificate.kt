package com.monofire.bestcertificate.models

data class Certificate(
    val categoryId: String,
    var certificateCategory: String,
    var certificateList: List<CertificateItem>
)