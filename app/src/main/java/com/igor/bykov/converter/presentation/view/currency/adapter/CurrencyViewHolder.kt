package com.igor.bykov.converter.presentation.view.currency.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.facebook.drawee.view.SimpleDraweeView
import com.igor.bykov.converter.R
import com.igor.bykov.converter.presentation.view.currency.event.UICurrencyChangedEvent
import com.igor.bykov.converter.presentation.view.currency.event.UICurrencySelectedEvent
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import com.igor.bykov.converter.presentation.view.utils.BaseViewHolder
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

/**
 * ViewHolder for [CurrencyViewModel]
 */
class CurrencyViewHolder(val view: ViewGroup):
    BaseViewHolder<CurrencyViewModel>(inflate(view, R.layout.row_currency)), TextWatcher {

  @BindView(R.id.currencyName) lateinit var name: TextView
  @BindView(R.id.currencyRate) lateinit var rate: EditText
  @BindView(R.id.currencyFlag) lateinit var flagImage: SimpleDraweeView

  private val subject: PublishSubject<String> by lazy { PublishSubject.create<String>() }
  private var dis: Disposable? = null

  override fun bindModel(model: CurrencyViewModel) {
    this.model = model
    name.text = model.name
    flagImage.setImageURI("http://${model.name}")
    updateUI(model.rate)
    dis = subject.debounce(100, TimeUnit.MILLISECONDS)
        .filter { model.rate.toString() != it }
        .subscribe {
          EventBus.getDefault().post(UICurrencyChangedEvent(model.name,
              it.takeIf { !it.isEmpty() }
                  ?.filter { it.toString().matches(Regex("[.0-9]+")) }
                  ?.toFloat() ?: 0F))
        }
  }

  override fun afterTextChanged(s: Editable?) {
    subject.onNext(s.toString())
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    //Stub
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    //Stub
  }

  @OnClick(R.id.currencyRootView, R.id.currencyRate) fun currencyRootViewSelect() {
    if(adapterPosition != 0) {
      rate.requestFocus()
      EventBus.getDefault().post(UICurrencySelectedEvent(adapterPosition, model))
    }
  }

  fun clear() {
    rate.removeTextChangedListener(this)
    dis?.dispose()
  }

  fun updateUI(rates: Float) {
    rate.removeTextChangedListener(this)
    val text = rates.toString()
    rate.setText(text, TextView.BufferType.EDITABLE)
    rate.setSelection(rate.text?.length ?: 0)
    rate.addTextChangedListener(this)
  }
}