"use strict"
// 오리지날 자바스크립트
function CheckExtension(x){
	let flag = true
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
	let maxSize = 5242880
	
	if(x.fsize >= maxSize){
		alert('파일사이즈 초과')
		flag = false
	}
	if(regex.test(x.fname)){
		alert('해당 종류의 파일은 업로드 할 수 없습니다.')
		flag = false
	}
	return flag
}
$.prototype.nullChecker =x=>{	//	x - 배열, input 여러개
	let flag = false
	let i = 0
	for ( i in x ){
		if( x[i] === ''){
			flag = true
		}
	}
	return flag
}
// 프로토타입 안되면 위에 function 사용
/*$.prototype.checkExtension =x=>{
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$")
	let maxSize = 5242880
	
	if(x.fsize >= maxSize){
		alert('파일사이즈 초과')
		return false
	}
	if(regex.test(x.fname)){
		alert('해당 종류의 파일은 업로드 할 수 없습니다.')
		return false
	}
	return true
}*/