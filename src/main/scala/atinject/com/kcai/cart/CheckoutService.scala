package atinject.com.kcai.cart

import scala.annotation.tailrec

/**
  * Service does the checkout
  */
class CheckoutService(items: List[String]) extends ItemRepository {


  def basket = items.foldLeft(List.empty[Item])((savedItems: List[Item], x: String) =>
    getItem(x) match {
      case None => savedItems
      case Some(e) => e :: savedItems
    }
  )

  def checkOut = {
    @tailrec
    def sum(savedItems: List[Item], total: BigDecimal): BigDecimal = {
      savedItems match {
        case Nil => total
        case x :: tail =>
          sum(tail, total + x.price)
      }
    }
    sum(basket, 0.00)
  }
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
