package com.lightfeather.task.ext

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lightfeather.task.R
import com.lightfeather.task.presentation.model.UiState
import es.dmoral.toasty.Toasty

fun FragmentActivity.startLoading() {
    findViewById<ProgressBar>(R.id.progressBar).apply {
        visibility = ProgressBar.VISIBLE
    }
}

fun FragmentActivity.stopLoading() {
    findViewById<ProgressBar>(R.id.progressBar).apply {
        visibility = ProgressBar.GONE
    }
}

fun Fragment.startLoading() = activity?.startLoading()

fun Fragment.stopLoading() = activity?.startLoading()

fun <T> Fragment.handleUiState(
    uiState: UiState<T>,
    onIdle: () -> Unit = { stopLoading() },
    onLoading: () -> Unit = { startLoading() },
    onError: (Throwable) -> Unit = {
        Toasty.error(requireContext(), "Error ${it.localizedMessage}").show()
    },
    onSuccess: (T) -> Unit,
) {
   when (uiState){
       is UiState.Idle -> onIdle()
       is UiState.Loading -> onLoading()
       is UiState.Error -> onError(uiState.t)
       is UiState.Success -> onSuccess(uiState.data)
   }
}