import { Page } from "@playwright/test";
import { ProductLocators } from "../locators/ProductLocators";

export class ProductPage {

    readonly page: Page;

    readonly locator = new ProductLocators();

    constructor(page: Page) {

        this.page = page;

    }

    async clickAddToCart(): Promise<void> {

        const addToCartButton = this.page.locator(this.locator.addToCart);

        await addToCartButton.waitFor({ state: "visible" });
        await addToCartButton.click();

    }

}
