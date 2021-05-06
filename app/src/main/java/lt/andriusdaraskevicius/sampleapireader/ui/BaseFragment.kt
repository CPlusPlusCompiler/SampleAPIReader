package lt.andriusdaraskevicius.sampleapireader.ui

import android.app.AlertDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import lt.andriusdaraskevicius.sampleapireader.R

open class BaseFragment(@LayoutRes layoutId: Int): Fragment(layoutId) {

    protected fun setFragmentTitle(title: String) {
        requireActivity().title = title
    }


    protected fun showErrorMessage(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_occured)
            .setMessage(message)
            .setNegativeButton(android.R.string.cancel) {_, _ ->}
            .setNeutralButton(android.R.string.ok) {_, _ ->}
            .setPositiveButton(android.R.string.ok) {_, _ ->}
            .show()
    }

}