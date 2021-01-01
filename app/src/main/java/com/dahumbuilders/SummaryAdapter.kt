package com.dahumbuilders

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.scale
import androidx.recyclerview.widget.RecyclerView
import com.dahumbuilders.model.Summary
import kotlinx.android.synthetic.main.item_summary.view.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat


class SummaryAdapter(private val summary: List<Summary>) :
    RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_summary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sum = summary[position]
        holder.summaryDate.text = holder.toDateFormat(sum.DatePaid)

        holder.summaryTotalCash.text = holder.formatAmount(sum.TotalCash)
        holder.summaryTotalCheck.text = holder.formatAmount(sum.TotalCheck)
        holder.summaryTotalBankTrans.text = holder.formatAmount(sum.TotalBankTransfer)
        holder.summaryExpenses.text = holder.formatAmount(sum.Expenses)
        holder.summaryTotalCashOnHand.text = holder.formatAmount(sum.TotalCashOnHand)

        holder.summaryTotalCashLabel.text = holder.formatLabel(holder.summaryTotalCashLabel.text)
        holder.summaryTotalCheckLabel.text = holder.formatLabel(holder.summaryTotalCheckLabel.text)
        holder.summaryTotalBankTransLabel.text = holder.formatLabel(holder.summaryTotalBankTransLabel.text)
        holder.summaryExpensesLabel.text = holder.formatLabel(holder.summaryExpensesLabel.text)
        holder.summaryTotalCashOnHandLabel.text = holder.formatLabel(holder.summaryTotalCashOnHandLabel.text)

    }

    override fun getItemCount(): Int {
        return summary.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val summaryDate = itemView.summaryDate!!
        val summaryTotalCash = itemView.summaryTotalCash!!
        val summaryTotalCheck = itemView.summaryTotalCheck!!
        val summaryTotalBankTrans = itemView.summaryTotalBankTrans!!
        val summaryExpenses = itemView.summaryExpenses!!
        val summaryTotalCashOnHand = itemView.summaryTotalCashOnHand!!

        val summaryTotalCashLabel = itemView.summaryTotalCashLabel!!
        val summaryTotalCheckLabel = itemView.summaryTotalCheckLabel!!
        val summaryTotalBankTransLabel = itemView.summaryTotalBankTransLabel!!
        val summaryExpensesLabel = itemView.summaryExpensesLabel!!
        val summaryTotalCashOnHandLabel = itemView.summaryTotalCashOnHandLabel!!

        @SuppressLint("SimpleDateFormat")
        fun toDateFormat(datePaid: String?): SpannableStringBuilder {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(datePaid!!)
            val pattern = "MMMM dd, yyyy"
            val formatted = SimpleDateFormat(pattern).format(date!!)
            return SpannableStringBuilder().scale(1.3F) { append(formatted) }
        }

        fun formatAmount(value: Double): SpannableStringBuilder {
            val formatted = DecimalFormat("#,##0.00").format(BigDecimal(value))

            return SpannableStringBuilder()
                .bold { scale(1.1F) { append(formatted) } }
        }

        fun formatLabel(text: CharSequence): SpannableStringBuilder {
            return SpannableStringBuilder()
                .bold { color(Color.RED) { scale(1.1F) { append(text) } }
                }
        }
    }
}
