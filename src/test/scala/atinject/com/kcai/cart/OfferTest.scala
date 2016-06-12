package atinject.com.kcai.cart

import org.scalatest.{FlatSpec, Matchers}


class OfferTest extends FlatSpec with Matchers {

  "None offer on one Apple" should "have price of 0.60" in {
    val offer = new OfferService
    offer.calculate(1, 0.60, None) should be(0.60)
  }

  "BuyOneGetOneFree offer on one Apple" should "have price of 0.60" in {
    val offer = new OfferService
    offer.calculate(1, 0.60, Some(new BuyForPriceOfOffer(2, 1))) should be(0.60)
  }

  "BuyOneGetOneFree offer on two Apples" should "have price of 0.60" in {
    val offer = new OfferService
    offer.calculate(2, 0.60, Some(new BuyForPriceOfOffer(2, 1))) should be(0.60)
  }

  "BuyOneGetOneFree offer on three Apples" should "have price of 1.20" in {
    val offer = new OfferService
    offer.calculate(3, 0.60, Some(new BuyForPriceOfOffer(2, 1))) should be(1.20)
  }

  "BuyOneGetOneFree offer on four Apples" should "have price of 2.40" in {
    val offer = new OfferService
    offer.calculate(4, 0.60, Some(new BuyForPriceOfOffer(2, 1))) should be(1.20)
  }

  "BuyOneGetOneFree offer on five Apples" should "have price of 1.80" in {
    val offer = new OfferService
    offer.calculate(5, 0.60, Some(new BuyForPriceOfOffer(2, 1))) should be(1.80)
  }

  "BuyThreeForTwo offer on an Orange" should "have price of 0.25" in {
    val offer = new OfferService
    offer.calculate(1, 0.25, Some(new BuyForPriceOfOffer(3, 2))) should be(0.25)
  }

  "BuyThreeForTwo offer on two Oranges" should "have price of 0.50" in {
    val offer = new OfferService
    offer.calculate(2, 0.25, Some(new BuyForPriceOfOffer(3, 2))) should be(0.50)
  }

  "BuyThreeForTwo offer on three Oranges" should "have price of 0.50" in {
    val offer = new OfferService
    offer.calculate(3, 0.25, Some(new BuyForPriceOfOffer(3, 2))) should be(0.50)
  }

  "BuyThreeForTwo offer on four Oranges" should "have price of 0.75" in {
    val offer = new OfferService
    offer.calculate(4, 0.25, Some(new BuyForPriceOfOffer(3, 2))) should be(0.75)
  }

}
