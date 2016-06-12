package atinject.com.kcai.cart

import scala.annotation.tailrec

/**
  * Service does the checkout
  */
class CheckoutService(items: List[String]) extends ItemRepository {


  def getBasket = items.foldLeft(List.empty[Item])((savedItems: List[Item], x: String) =>
    getItem(x) match {
      case Some(e) => e :: savedItems
      case None => savedItems
    }
  )
}

/**
  * Simple in-memory items repository
  */
object ItemRepository {
  val items = Map(
    "APPLE" -> Item("APPLE", 0.60),
    "ORANGE" -> Item("ORANGE", 0.25)
  )
}

sealed trait ItemRepository {
  import ItemRepository._

  def getItem(name: String) = {
    items.get(name)
  }
}

case class Item(name: String, price: BigDecimal) {
  override val toString = name
}
