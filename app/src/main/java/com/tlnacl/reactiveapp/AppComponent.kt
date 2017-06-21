package com.tlnacl.reactiveapp

import com.tlnacl.reactiveapp.ui.main.MainActivity
import com.tlnacl.reactiveapp.ui.shop.ShopActivity
import dagger.Component
import javax.inject.Singleton

@Component(
  modules = arrayOf(
      AndroidModule::class
  )
)
@Singleton
interface AppComponent {
  fun inject(into: MainActivity)
  fun inject(into: ShopActivity)
}