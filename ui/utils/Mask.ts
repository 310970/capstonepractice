export class Mask {

    static phone(number: string): string {

        return number.replace(/\d(?=\d{4})/g, "*");

    }

}