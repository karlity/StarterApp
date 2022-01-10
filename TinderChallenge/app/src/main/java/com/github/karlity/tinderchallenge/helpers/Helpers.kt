package com.github.karlity.tinderchallenge.helpers

import android.view.SurfaceControl

class Helpers {

}

sealed class TransactionRow {
    data class Header(val month: String) : TransactionRow()
    data class Transactions(val transactions: SurfaceControl.Transaction) : TransactionRow()
}

enum class Title(val title: String) {
    POSTED("Posted Transactions"),
    EXTERNAL("External Transactions"),
    SCHEDULED("Scheduled Transactions")
}