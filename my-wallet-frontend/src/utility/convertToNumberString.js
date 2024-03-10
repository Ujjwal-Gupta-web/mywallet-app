export const convertToNumberString=(number)=> {
    let numStr = number.toString();
    let parts = numStr.split('.');
    let integerPart = parts[0];
    let integerStr = '';
    let i = integerPart.length - 1;
    let count = 0;

    while (i >= 0) {
        integerStr = integerPart[i] + integerStr;
        count++;
 
        if (count % 3 === 0 && i !== 0) {
            integerStr = ',' + integerStr;
        }
        i--;
    }

    let result = parts.length > 1 ? integerStr + '.' + parts[1] : integerStr;
    return result;
}
