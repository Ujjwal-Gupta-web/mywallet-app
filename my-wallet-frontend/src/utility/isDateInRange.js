export const isDateInRange = (givenStart, givenEnd, givenDate) => {
    var start = new Date(givenStart)
    var end = new Date(givenStart);
    var given = new Date(givenDate);
    var objYear = given.getFullYear();
    var objMonth = given.getMonth();
    var objDay = given.getDate();
    // Extract year, month, and day from the start date
    var startYear = start.getFullYear();
    var startMonth = start.getMonth();
    var startDay = start.getDate();
    // Extract year, month, and day from the end date
    var endYear = end.getFullYear();
    var endMonth = end.getMonth();
    var endDay = end.getDate();
    // Check if the object's date falls within the range
    return objYear >= startYear && objYear <= endYear &&
        objMonth >= startMonth && objMonth <= endMonth &&
        objDay >= startDay && objDay <= endDay;
}
