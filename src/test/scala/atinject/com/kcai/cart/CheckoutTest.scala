package atinject.com.kcai.cart

import org.scalatest.FlatSpec
import org.scalatest.Matchers

/**
  * Created by kcai on 12/06/2016.
  */
class CheckoutTest extends FlatSpec with Matchers {
  "A checkout system" should "have items in basket" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "ORANGE"))
    checkoutService.getBasket.size should be(3)
  }

  // assume ZERO item in basket is allowed
  "A checkout system" can "have ZERO item in basket" in {
    val checkoutService = new CheckoutService(Nil)
    checkoutService.getBasket.size should be(0)
  }
}
