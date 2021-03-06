package atinject.com.kcai.cart

import org.scalatest.FlatSpec
import org.scalatest.Matchers

/**
  * Created by kcai on 12/06/2016.
  */
class CheckoutTest extends FlatSpec with Matchers {
  "A checkout system" should "have items in basket" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "ORANGE"))
    checkoutService.basket.values.flatten.size should be(3)
  }

  // assume ZERO item in basket is allowed
  "A checkout system" can "have ZERO item in basket" in {
    val checkoutService = new CheckoutService(Nil)
    checkoutService.basket.values.flatten.size should be(0)
  }

  "A checkout system" must "have only Apple or Orange, non-existent item is ignored" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "ORANGE", "NON-EXISTENCE"))
    checkoutService.basket.values.flatten.size should be(3)
  }

  "A checkout system" should "have Apple priced at 0.60" in {
    val checkoutService = new CheckoutService(List("APPLE"))
    checkoutService.basket.find(_._1.name == "APPLE") match {
      case Some(x) => x._1.price should be(0.60)
      case None => fail("No correct item found")
    }
  }

  "A checkout system" should "have Orange priced at 0.25" in {
    val checkoutService = new CheckoutService(List("ORANGE"))
    checkoutService.basket.find(_._1.name == "ORANGE") match {
      case Some(x) => x._1.price should be(0.25)
      case None => fail("No correct item found")
    }
  }

  "A checkout system" should "calculate the basket total" in {
    val checkoutService = new CheckoutService(List("APPLE", "ORANGE", "APPLE", "APPLE"))
    checkoutService.checkOut should be(1.45)
  }

  "A checkout system" must "calculate the total price of saved items in basket Apple only" in {
    val checkoutService = new CheckoutService(List("APPLE"))
    checkoutService.checkOut should be(0.60)
  }

  "A checkout system" must "calculate the total price of saved items in basket Orange only" in {
    val checkoutService = new CheckoutService(List("ORANGE"))
    checkoutService.checkOut should be(0.25)
  }

  "A checkout system" must "calculate the total price of saved items in basket with mixed selections" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "ORANGE"))
    checkoutService.checkOut should be(1.10)
  }




  "A checkout system" should "calculate BuyOneGetOneFree offer for 2 Apples" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "APPLE"))
    checkoutService.checkOut should be(0.85)
  }

  "A checkout system" should "calculate BuyOneGetOneFree offer for 3 Apples" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "APPLE", "APPLE"))
    checkoutService.checkOut should be(1.45)
  }

  "A checkout system" should "calculate BuyOneGetOneFree offer for 4 Apples" in {
    val checkoutService = new CheckoutService(List("ORANGE", "APPLE", "APPLE", "APPLE", "APPLE"))
    checkoutService.checkOut should be(1.45)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 1 Oranges" in {
    val checkoutService = new CheckoutService(List("ORANGE"))
    checkoutService.checkOut should be(0.25)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 2 Oranges" in {
    val checkoutService = new CheckoutService(List("ORANGE", "ORANGE"))
    checkoutService.checkOut should be(0.50)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 3 Oranges" in {
    val checkoutService = new CheckoutService(List("ORANGE", "ORANGE", "ORANGE"))
    checkoutService.checkOut should be(0.50)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 4 Oranges" in {
    val checkoutService = new CheckoutService(List("ORANGE", "ORANGE", "ORANGE", "ORANGE"))
    checkoutService.checkOut should be(0.75)
  }

  "A checkout system" should "calculate BuyThreeForTwo and BuyOneGetOneFree offers for mixed selections" in {
    val checkoutService = new CheckoutService(List("ORANGE", "ORANGE", "ORANGE", "ORANGE", "APPLE", "APPLE", "APPLE"))
    checkoutService.checkOut should be(1.95)
  }


}
