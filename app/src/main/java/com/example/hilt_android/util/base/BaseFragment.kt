package com.example.hilt_android.util.base

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.example.hilt_android.R
import com.example.hilt_android.network.ErrorType
import com.example.hilt_android.ui.MainActivity
import com.example.hilt_android.util.Utils
import com.example.hilt_android.util.longSnackbar
import com.example.hilt_android.util.snackbar
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseFragment : Fragment() {

    protected val unknownErrorMessage: String by lazy {
        Utils.getString(R.string.unknown_error)
    }
    protected val timeoutErrorMessage: String by lazy {
        Utils.getString(R.string.try_again)
    }
    protected val noInternetError: String by lazy {
        Utils.getString(R.string.no_internet)
    }

    protected var fragmentView: View? = null

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentView = null
    }

    fun showSnackError(msg: String, long: Boolean = false) {
        val snack = if (!long) fragmentView?.snackbar(msg)
        else fragmentView?.longSnackbar(msg)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorRedError
            )
        )
        val textView =
            snack?.view?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView //Get reference of snackbar textview
        textView.maxLines = 5
        snack.show()
    }

    fun showSnackError(msg: Int, long: Boolean = false) {
        val snack = if (!long) fragmentView?.snackbar(msg)
        else fragmentView?.longSnackbar(msg)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorRedError
            )
        )
        val textView =
            snack?.view?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView //Get reference of snackbar textview
        textView.maxLines = 5
        snack.show()
    }

    fun showSnackSuccess(msg: String, long: Boolean = false) {
        val snack = if (!long) fragmentView?.snackbar(msg)
        else fragmentView?.longSnackbar(msg)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorSuccess
            )
        )
        snack?.show()
    }

    fun showSnackSuccess(msg: Int, long: Boolean = false) {
        val snack = if (!long) fragmentView?.snackbar(msg)
        else fragmentView?.longSnackbar(msg)
        snack?.view?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorSuccess
            )
        )
        snack?.show()
    }

    fun loadingProgress(boolean: Boolean) {
        if (boolean) {
            (activity as MainActivity).loading_layout.visibility = View.VISIBLE
        } else {
            (activity as MainActivity).loading_layout.visibility = View.GONE
        }
    }

    fun showErrorMessage(errorType: ErrorType) {
        when (errorType) {
            ErrorType.NETWORK -> showSnackError(noInternetError)
            ErrorType.TIMEOUT -> showSnackError(timeoutErrorMessage)
            ErrorType.UNKNOWN -> showSnackError(unknownErrorMessage)
            ErrorType.SESSION_EXPIRED -> {
            }
        }
    }

    protected fun getDefaultNavOptions(fade: Boolean = false) = navOptions {
        if (!fade) {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popExit = R.anim.slide_out_right
                popEnter = R.anim.slide_in_left
            }
        } else {
            anim {
                enter = R.anim.fade_in
                exit = R.anim.fade_out
                popExit = R.anim.fade_out
                popEnter = R.anim.fade_in
            }
        }
    }

}