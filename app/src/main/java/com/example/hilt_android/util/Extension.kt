package com.example.hilt_android.util

import android.content.Context
import android.content.ContextWrapper
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.liveData
import com.example.hilt_android.network.ResponseResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import java.util.regex.Pattern

inline fun <T> liveResponse(crossinline body: suspend () -> ResponseResult<T>) =
    liveData(Dispatchers.IO) {
        emit(ResponseResult.Pending)
        val result = body()
        emit(result)
        emit(ResponseResult.Complete)
    }

inline fun <T> listLiveResponse(crossinline body: suspend () -> ResponseResult<List<T>>) =
    liveData(Dispatchers.IO) {
        emit(ResponseResult.Pending)
        var result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        result = body()
        emit(result)
        emit(ResponseResult.Complete)
    }


fun String.isAnEmailAddress(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun EditText.textString(): String = this.text.toString()

fun String.validPhoneNumber(): Boolean {
    return if (!Pattern.matches("[a-zA-Z]+", this)) {
        !(this.length < 6 || this.length > 13)
    } else {
        false
    }
}

fun ViewGroup.inflate(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun Context.inflateView(layout: Int): View {
    val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return inflater.inflate(layout, null)
}

fun Context.inflateCustomView(layout: Int, viewGroup: ViewGroup?) {
    val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    inflater.inflate(layout, viewGroup, true)
}

fun ViewGroup.setLayoutChangeAnim(layoutChange: Int) {
    this.layoutTransition.enableTransitionType(layoutChange)
}

fun BottomNavigationView.getSelectedItem() = menu.findItem(selectedItemId)

fun BottomNavigationView.getMenuItem(id: Int) = menu.findItem(id)

fun View.getParentActivity(): AppCompatActivity? = this.context.getParentActivity()

fun Context.getParentActivity(): AppCompatActivity? {
    return when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> this.baseContext.getParentActivity()
        else -> null
    }
}

fun Fragment.longToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}

fun Fragment.longToast(msg: Int) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.longToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.snackbar(msg: Int) = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
fun View.snackbar(msg: String) = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
fun View.longSnackbar(msg: String) = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
fun View.longSnackbar(msg: Int) = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
fun View.indefiniteSnackbar(msg: String) = Snackbar.make(this, msg, Snackbar.LENGTH_INDEFINITE)
fun View.indefiniteSnackbar(msg: Int) = Snackbar.make(this, msg, Snackbar.LENGTH_INDEFINITE)


fun AppCompatActivity.longToast(msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

//fun ImageView.loadImageWithGlide(imgUrl: String?){
//    imgUrl?.let {
//        Glide.with(this.context)
//            .load(imgUrl)
//            .into(this)
//    }
//}

////From Epoxy Wiki
//inline fun <T> CarouselModelBuilder.withModelsFrom(items: List<T>, modelBuilder: (T) -> EpoxyModel<*>){
//    models(items.map { modelBuilder(it) })
//}