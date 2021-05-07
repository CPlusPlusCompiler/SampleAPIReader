package lt.andriusdaraskevicius.sampleapireader.ui

import android.app.AlertDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import lt.andriusdaraskevicius.sampleapireader.R

open class BaseFragment(@LayoutRes layoutId: Int): Fragment(layoutId) {

    protected fun setFragmentTitle(title: String) {
        requireActivity().title = title
    }


    protected fun showErrorMessage(message: String, onRetry: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_occurred)
            .setMessage(message)
            .setNegativeButton(android.R.string.cancel) {_, _ ->}
            .setPositiveButton(R.string.retry) {_, _ ->
                onRetry.invoke()
            }
            .show()
    }

}