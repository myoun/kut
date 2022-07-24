package app.myoun.kut.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SHA256 {
    companion object {
        val INSTANCE = SHA256()
    }

    @Throws(NoSuchAlgorithmException::class)
    fun encrypt(text: String): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-256")
        md.update(text.toByteArray())
        return bytesToHex(md.digest())
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val builder = StringBuilder()
        for (b in bytes) {
            builder.append(String.format("%02x", b))
        }
        return builder.toString()
    }
}

fun String.encryptSHA256() = SHA256.INSTANCE.encrypt(this)
