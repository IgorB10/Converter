package com.igor.bykov.converter.presentation.view.currency

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.igor.bykov.converter.R
import com.igor.bykov.converter.presentation.view.BaseFragment
import com.igor.bykov.converter.presentation.view.currency.adapter.CurrencyAdapter
import com.igor.bykov.converter.presentation.view.currency.event.UICurrencyChangedEvent
import com.igor.bykov.converter.presentation.view.currency.event.UICurrencySelectedEvent
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import kotlinx.android.synthetic.main.fragment_list_currency.rvCurrency
import org.greenrobot.eventbus.Subscribe
import java.util.Collections
import javax.inject.Inject

/**
 * Currency list view
 */
class CurrencyListFragment: BaseFragment(), CurrencyView {

  @Inject lateinit var presenter: CurrencyPresenter

  private val adapter: CurrencyAdapter by lazy { CurrencyAdapter(Collections.emptyList<CurrencyViewModel>()) }
  private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(activity) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.bind(this)
  }

  override fun bindLifeCycle() = presenter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    return inflater.inflate(R.layout.fragment_list_currency, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    this.setUp()
  }

  override fun setUp() {
    rvCurrency.layoutManager = layoutManager
    val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
    rvCurrency.layoutAnimation = controller
    rvCurrency.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    rvCurrency.adapter = adapter
  }

  override fun render(currencyModel: MutableList<CurrencyViewModel>,
      diffResult: DiffUtil.DiffResult?) {
    adapter.updateItems(currencyModel, diffResult)
  }

  override fun renderError(error: Int) {
    Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()
  }

  @Subscribe fun onUICurrectSelectedEvent(event: UICurrencySelectedEvent) {
    event.model?.apply {
      presenter.loadCurrency(event.model.name, event.model.rate)
    }
    layoutManager.scrollToPosition(0)
  }

  @Subscribe fun onUICurrencyChangedEvent(event: UICurrencyChangedEvent) {
    presenter.loadCurrency(event.name, event.rate)
  }
}