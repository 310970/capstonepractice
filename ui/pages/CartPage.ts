import { Page } from "@playwright/test";
import { CartLocators } from "../locators/CartLocators";

export class CartPage {

    readonly page: Page;

    readonly locator = new CartLocators();

    constructor(page: Page) {

        this.page = page;

    }

}