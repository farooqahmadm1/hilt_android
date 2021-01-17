package com.ibex.fleetmanager.common.utility

import android.content.Context
import com.ibex.fleetmanager.common.model.FirebaseMessage
import java.io.File

object FileUtils {
    fun checkIfVoiceFileExits(data: FirebaseMessage, context: Context): Boolean {
        val internalDirectory = context.externalCacheDir
        val directory = File(internalDirectory, "voiceNotes/${data.tripID}/")
        return if (!directory.mkdirs()) {
            directory.mkdirs()
            File("${directory.absolutePath}/${data.fileName}").exists()
        } else {
            File("${directory.absolutePath}/${data.fileName}").exists()
        }
    }

    fun getFileName(context: Context, tripID: String, passengerID: String): String {
        val internalDirectory = context.externalCacheDir
        val directory = File(internalDirectory, "voiceNotes/$tripID/")
        if (!directory.mkdirs()) {
            directory.mkdirs()
        }
        return "${directory.absolutePath}/${passengerID}${System.currentTimeMillis()}.3gp"
    }

    fun getFileForPlayingAndDownload(
        context: Context,
        directory: String,
        fileName: String
    ): String {
        val internalDirectory = context.externalCacheDir
        val directory = File(internalDirectory, "voiceNotes/$directory/")
        if (!directory.mkdirs()) {
            directory.mkdirs()
        }
        return "${directory.absolutePath}/${fileName}"
    }

}