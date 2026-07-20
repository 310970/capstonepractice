import { Page } from "@playwright/test";
import { HomeLocators } from "../locators/HomeLocators";

export class HomePage {

    readonly page: Page;

    readonly locator = new HomeLocators();

    constructor(page: Page) {

        this.page = page;

    }

    async searchProduct(product: string): Promise<void> {

        const searchBox = this.page.locator(this.locator.searchBox);

        await searchBox.waitFor({ state: "visible" });
        await searchBox.fill(product);
        await searchBox.press("Enter");

    }

}
