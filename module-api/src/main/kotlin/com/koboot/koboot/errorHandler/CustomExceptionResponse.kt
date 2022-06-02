package com.koboot.koboot.errorHandler

class CustomExceptionResponse(
    val errorCode: String = "",
    val detail: String = ""
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()

        class Builder {
            private var errorCode: String = ""
            private var detail: String = ""

            fun errorCode(errorCode: String) = apply { this.errorCode = errorCode }
            fun detail(detail: String) = apply { this.detail = detail }

            fun build() = CustomExceptionResponse(errorCode, detail)
        }
    }
}