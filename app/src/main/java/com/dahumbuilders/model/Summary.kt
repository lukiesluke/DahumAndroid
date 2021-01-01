package com.dahumbuilders.model

import android.os.Parcel
import android.os.Parcelable

data class Summary(
    val DatePaid: String?, val TotalCash: Double, val TotalCheck: Double,
    val TotalBankTransfer: Double, val Expenses: Double, val TotalCashOnHand: Double
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readDouble(),
        source.readDouble(),
        source.readDouble(),
        source.readDouble(),
        source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(DatePaid)
        writeDouble(TotalCash)
        writeDouble(TotalCheck)
        writeDouble(TotalBankTransfer)
        writeDouble(Expenses)
        writeDouble(TotalCashOnHand)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Summary> = object : Parcelable.Creator<Summary> {
            override fun createFromParcel(source: Parcel): Summary = Summary(source)
            override fun newArray(size: Int): Array<Summary?> = arrayOfNulls(size)
        }
    }
}