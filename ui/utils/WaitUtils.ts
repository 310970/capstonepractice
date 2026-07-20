import { Locator } from "@playwright/test";

export class WaitUtils {

    static async wait(locator: Locator) {

        await locator.waitFor();

    }

}