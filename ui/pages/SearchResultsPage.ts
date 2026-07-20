import { Page } from "@playwright/test";
import { SearchResultsLocators } from "../locators/SearchResultsLocators";

export class SearchResultsPage {

    readonly page: Page;

    readonly locator = new SearchResultsLocators();

    constructor(page: Page) {

        this.page = page;

    }

    async clickFourthProduct(): Promise<Page> {

        const productLinks = this.page.locator(this.locator.productLinks);

        await productLinks.first().waitFor({ state: "visible" });

        const [productPage] = await Promise.all([
            this.page.waitForEvent("popup"),
            productLinks.nth(3).click(),
        ]);

        await productPage.waitForLoadState("domcontentloaded");
        return productPage;

    }

}
