package com.monofire.bestcertificate.models

data class CertificateItem(
    var certificateId: String,
    var certificateImage: String,
    var isLocked: Boolean,
    var certificateMap: List<TextsMap>
)