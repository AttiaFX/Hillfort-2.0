package org.wit.hillfort.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.HillfortStore

var lastId = 0L

internal fun getId(): Long {
  return lastId++
}

class HillfortMemStore : HillfortStore, AnkoLogger {

  val hillforts = ArrayList<HillfortModel>()

  override fun findAll(): List<HillfortModel> {
    return hillforts
  }

  override fun create(hillfort: HillfortModel) {
    hillfort.id = getId()
    hillforts.add(hillfort)
    logAll()
  }

  override fun update(hillfort: HillfortModel) {
    var foundHillfort: HillfortModel? = hillforts.find { p -> p.id == hillfort.id }
    if (foundHillfort != null) {
      foundHillfort.title = hillfort.title
      foundHillfort.description = hillfort.description
      foundHillfort.image = hillfort.image
      foundHillfort.rating = hillfort.rating
      foundHillfort.favorite = hillfort.favorite
      foundHillfort.location = hillfort.location
      logAll();
    }
  }

  override fun delete(hillfort: HillfortModel) {
    hillforts.remove(hillfort)
  }

  override fun clear() {
    hillforts.clear()
  }

  override fun findById(id:Long) : HillfortModel? {
    val foundHillfort: HillfortModel? = hillforts.find { it.id == id }
    return foundHillfort
  }

  fun logAll() {
    hillforts.forEach { info("${it}") }
  }
}