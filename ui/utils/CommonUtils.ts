import { Page } from "@playwright/test";

export class CommonUtils {

    constructor(private page: Page) {}

    async pause(seconds: number) {

        await this.page.waitForTimeout(seconds * 1000);

    }

}