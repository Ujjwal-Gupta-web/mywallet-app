import { convertToNumberString } from "../utility/convertToNumberString";
import { formatDate } from "../utility/formatDate";
import { isDateInRange } from "../utility/isDateInRange";

test("formatDate", () => {
    const currentDate = '2024-02-13T12:38:02.686Z';
    const displayDate = formatDate(currentDate);
    expect(displayDate).toBe("Feb 13, 2024 6:08 PM")
})

test("isDateInRange", () => {
    const start = '2024-02-28';
    const end = '2024-03-01';
    const givenDate = '2024-02-28T17:16:15.649+00:00';
    const result = isDateInRange(start, end, givenDate);
    expect(result).toBeTruthy();

    const givenDate1 = '2024-02-22T15:07:12.581+00:00';
    const result1 = isDateInRange(start, end, givenDate1);
    expect(result1).toBeFalsy();
})

test("convertToNumberString", () => {
   const number=200;
    const result = convertToNumberString(number);
    expect(result).toBe("200");

    const number1=2000;
    const result1 = convertToNumberString(number1);
    expect(result1).toBe("2,000");

    const number2=200000;
    const result2 = convertToNumberString(number2);
    expect(result2).toBe("200,000");
})