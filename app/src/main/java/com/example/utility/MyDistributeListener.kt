package com.ibex.fleetmanager.common.utility

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails
import com.microsoft.appcenter.distribute.UpdateAction


class MyDistributeListener : DistributeListener {


    override fun onReleaseAvailable(activity: Activity?, releaseDetails: ReleaseDetails?): Boolean {
        // Look at releaseDetails public methods to get version information, release notes text or release notes URL
        var versionName: String = releaseDetails!!.shortVersion
        var versionCode: Int = releaseDetails.version
        var releaseNotes: String? = releaseDetails.releaseNotes
        var releaseNotesUrl: Uri? = releaseDetails.releaseNotesUrl


        // Build our own dialog title and message
        // Build our own dialog title and message
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        dialogBuilder.setTitle("Version $versionName available!") // you should use a string resource instead of course, this is just to simplify example
        dialogBuilder.setMessage(releaseNotes)


        // Mimic default SDK buttons
        // Mimic default SDK buttons
        dialogBuilder.setPositiveButton(
            com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_download
        ) { _, _ ->
            // This method is used to tell the SDK what button was clicked
            Distribute.notifyUpdateAction(UpdateAction.UPDATE)
        }

        // We can postpone the release only if the update is not mandatory
        // We can postpone the release only if the update is not mandatory
        if (!releaseDetails.isMandatoryUpdate) {
            dialogBuilder.setNegativeButton(
                com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_postpone
            ) { _, _ ->
                // This method is used to tell the SDK what button was clicked
                Distribute.notifyUpdateAction(UpdateAction.POSTPONE)
            }
        }
        dialogBuilder.setCancelable(false) // if it's cancelable you should map cancel to postpone, but only for optional updates

        dialogBuilder.create().show()

        // Return true if you are using your own dialog, false otherwise
        // Return true if you are using your own dialog, false otherwise
        return true
    }


}