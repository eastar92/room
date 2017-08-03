/**
 * 
 */

function checkValue(fObj) {
	var maxNum = fObj.elements.length;
	var result = "";
	for (var i = 0; i < maxNum; i++) {
		var eObj = fObj.elements[i];

		if (eObj.value != "전송") {
			if (i % 2 != 0 && isNaN(eObj.value)) {
				alert("숫자 입력하세요");
				eObj.value = "";
				eObj.focus();
				return false;
			}
			result += eObj.value;
		}
		if (i == maxNum - 1) {
			eObj.value = result;
		}
	}
	// 2,4,6 숫자만
	return false;
}