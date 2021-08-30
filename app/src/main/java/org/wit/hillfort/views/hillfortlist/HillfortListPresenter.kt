package org.wit.hillfort.views.hillfortlist

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddHillfort() {
    view?.navigateTo(VIEW.HILLFORT)
  }

  fun doEditHillfort(hillfort: HillfortModel) {
    view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
  }

  fun doShowHillfortsMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun doShowFavorites() {
    view?.navigateTo(VIEW.FAVORITES)
  }

  fun doShowHillforts() {
    view?.navigateTo(VIEW.LIST)
  }

  fun loadHillforts(onlyFavorites: Boolean) {
    doAsync {
      var hillforts = app.hillforts.findAll()
      if (onlyFavorites) {
        val favorites: MutableList<HillfortModel> = mutableListOf()
        for (hill in hillforts) {
          if (hill.favorite) {
            favorites.add(hill)
          }
        }
        hillforts = favorites
      }
      uiThread {
        view?.showHillforts(hillforts)
      }
    }
  }

  fun doLogout() {
    view?.navigateTo(VIEW.LOGIN)
  }
}