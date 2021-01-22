/**
 * 
 */

function confirm_password(){
	const password = document.querySelector("#password");
	const re_password = document.querySelector("#re_password");
	
	
	if(password.value===re_password.value)
	{
		return true;
	}else{
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
}

function alert_SignUp()
{
	alert("회원가입이 완료되었습니다.");
}
