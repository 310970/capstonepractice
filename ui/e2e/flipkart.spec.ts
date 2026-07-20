import { test } from "../fixtures/baseFixture";
import { ShoppingFlow } from "../flows/ShoppingFlow";

test("searches for a watch and adds the fourth product to the cart", async ({ page }) => {
  const shoppingFlow = new ShoppingFlow(page);

  await page.goto("https://www.flipkart.com");
  await shoppingFlow.searchAndOpenFourthProduct("watch");
  await shoppingFlow.addProductToCart();
});
