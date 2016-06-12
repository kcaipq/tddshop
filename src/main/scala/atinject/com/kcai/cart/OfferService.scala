package atinject.com.kcai.cart

import scala.math.BigDecimal.RoundingMode

/**
  * Offer service which calculates the the total price with offers applied to
  */
class OfferService {

  /**
    * Calculate price with offer. Assume only one offer can be applied to an item at a time
    * @param quantity the basket quantity for an item
    * @param price the single price for an item
    * @param offerable whether the item is on offer
    * @return the calcualted final prices for applied items
    */
  def calculate(quantity: Int, price: BigDecimal, offerable: Option[Offerable]) = {
    val offer = offerable match {
      case Some(bf: BuyForPriceOfOffer) =>
        new BuyForPriceOfOffer(bf.offerQuantity, bf.forPriceQuantity)

      case _ => new BuyForPriceOfOffer(1, 1)
    }

    (offer.getQuantity(quantity) * price).setScale(2, RoundingMode.HALF_EVEN).toDouble
  }
}

/**
  * May have other offer types
  */
sealed trait Offerable {
  val stackable = false // assume not stackable
  val offerQuantity: Int
  val forPriceQuantity: Int
}

/**
  * Buy X for the price of Y offer
  * @param offerQuantity the original quantity in the offer
  * @param forPriceQuantity the pricable quantity
  */
class BuyForPriceOfOffer(val offerQuantity: Int, val forPriceQuantity: Int) extends Offerable {
  def getQuantity(quantity: Int) = (quantity % offerQuantity) + (quantity / offerQuantity * forPriceQuantity)
}