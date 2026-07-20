import { Page } from "@playwright/test";
import { HomePage } from "../pages/HomePage";
import { SearchResultsPage } from "../pages/SearchResultsPage";
import { ProductPage } from "../pages/ProductPage";

export class ShoppingFlow {

    private homePage: HomePage;
    private searchResultsPage: SearchResultsPage;
    private productPage?: ProductPage;

    constructor(page: Page) {
        this.homePage = new HomePage(page);
        this.searchResultsPage = new SearchResultsPage(page);
    }

    async searchAndOpenFourthProduct(product: string) {

        await this.homePage.searchProduct(product);

        const productTab = await this.searchResultsPage.clickFourthProduct();
        this.productPage = new ProductPage(productTab);

    }

    async addProductToCart() {

        if (!this.productPage) {
            throw new Error("Open a product before adding it to the cart.");
        }

        await this.productPage.clickAddToCart();

    }

}
