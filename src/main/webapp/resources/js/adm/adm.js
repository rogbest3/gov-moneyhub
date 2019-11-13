"use strict"
var adm = adm || {}
adm =(()=>{
	const WHEN_ERR = '호출하는 js 파일을 찾지 못했습니다.'
	let _, js, css, img, navi_vue_js, navi_js, brd_vue_js
	let init=()=>{
/*		sessionStorage.setItem('ctx', '/web')
		_ = sessionStorage.getItem('ctx')}	*/
		_ = $.ctx()
		js = $.js()
		css = $.css()
		img = $.img()
		navi_vue_js = js + '/vue/navi_vue.js'
		navi_js = js + '/cmm/navi.js'
		brd_vue_js = js + '/vue/brd_vue.js'
	}
	
	let onCreate=()=>{
		init()
		alert('js : '+ js)
		$.when(
			$.getScript(navi_vue_js),
			$.getScript(navi_js),
			$.getScript(brd_vue_js)
		)
		.done(()=>{
			setContentView()
		})
		.fail(()=>{
			alert(WHEN_ERR)
		})
	}
	
	let setContentView=()=>{
	//	$('#login_form_id').remove()
	//	$('body').empty()
	//	$('head').html(brd_vue.brd_head())
		$('body').html('<div id="navi_id"></div>')
		.addClass('text-center')
		$(navi_vue.navi()).appendTo('#navi_id')
		$('#nav_scroller_id').remove()
		navi.onCreate()
		
		$('<main id="main" role="main" class="container">'+
		'<table id="tab">'+
		'  <tr>'+
		'  </tr>'+
		'</table>'+
		'</main>')  // key값 무조건 string이기 때문에 '' 생량가능 value는 생략 불가, json이기때문에 , 로 속성 추가							
		.appendTo('body')	// body에 오버로딩
		.css({ width : '100%', height : '100%'}) 
		$('#main table')
		.css({ width : '80%', height : '80%', border :'2px solid black', margin: '0 auto', 'margin-top' : '8%' }) 
		
		
		
		$.each(
			[{ id : 'left', width : '20%'}, 
			{ id : 'right', width : '80%'}], 
			(i, j)=>{
			$('<td id="'+ j.id +'"></td>')
			.css({border: '2px solid black', width: j.width, 'vertical-align': 'top'})
			.appendTo('#tab tr')
		})

		$.each([	// name을 주고 구분
			{txt : '웹크롤링', name : 'web_crawl'},
			{txt : '고객 관리', name : 'cust_mgmt'}, 
			{txt : '거래 관리', name : 'trd_mgmt'},
			{txt : '커뮤니티 관리', name : 'comm_mgmt'},
			{txt : '계좌번호 관리', name : 'accnt_mgmt'}, 
			{txt : '계좌번호이력 관리', name : 'acchis_mgmt'}, 
			{txt : '환율관리', name : 'exr_mgmt'}], 
			(i, j)=>{
				$('<div name="'+ j.name +'">'+ j.txt +'</div>')
				.appendTo('#left')
				.click(function(){
			//		let that = $(this).attr('name')
					$(this).addClass('active')
					$(this).siblings().removeClass('active')
					switch($(this).attr('name')){
					case 'web_crawl' :
						webCrawl()						
						break
					case 'cust_mgmt' : 
						cust_mgmt()
						break
					case 'comm_mgmt' :
						comm_mgmt()
						break
					case 'accnt_mgmt' :
						accnt_mgmt()
						break
					case 'acchis_mgmt' :
						acchis_mgmt()
						break
					case 'trd_mgmt' :
						trd_mgmt()
						break	
					case 'exr_mgmt' :
						exr_mgmt()
						break
					}
			})
		})
		$('#left div').css({border: '2px solid black', margin: 'auto 0', 'line-height': '50px'})
	}
	let webCrawl =()=>{
		$('#right').empty()
		$('<form id="crawl_form_id" action="">'+
			'  <select name="site" size="1" >'+	//	multiple
			'  </select>'+
			'  <br>'+
			'  <input class="form-control mr-sm-2" value="송금" type="text" placeholder="Search" aria-label="Search">'+
			'</form>')
		.addClass('form-inline my-2 my-lg-0')
		.appendTo('#right')
		$('#crawl_form_id').css({padding : '0 auto', 'padding-top' : '5%'  })	//'padding-top' : '5%' 
		$('#crawl_form_id select').css({ 'margin-left' : '20%' , 'margin-right' : '1%'})
		
		$.each(['직접입력', 'naver.com', 'daum.net', 'google.com', 'youtube.com'], (i, j)=>{
			$('<option value="'+ j +'">'+ j +'</option>')
			.appendTo('#crawl_form_id select')
		})
		
		$('<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>')
		.appendTo('#crawl_form_id')
		.click(e=>{
			e.preventDefault()		
			let arr = [$('form#crawl_form_id select[name="site"]').val(),
						$('form#crawl_form_id input[type="text"]').val()]
			
			if(	!$.fn.nullChecker(arr) ){			
		//		alert(arr[0] + ', '+ arr[1])
				$.getJSON( _	+ '/tx/crawling/' + arr[0] +'/' + arr[1], d=>{		// form 태그의 id란 뜻
					alert(d.msg)
				})
			}
		})
	}
	let cust_mgmt=()=>{
		$('#right').empty()
		$('<a>데이터베이스  생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/cmm/create/db', d=>{
				alert( 'DB 생성 성공 여부 : ' + d.msg )
			})
		})
		$('<a>고객 테이블 생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/customers/create/table', d=>{
				alert( '테이블 생성 성공 여부 : ' + d.msg )
			})
		})
		
		$('<a>고객 테이블 삭제</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/customers/delete', d=>{
				alert( '테이블 생성 성공 여부 : ' + d.msg )
			})
		})
		$('<a>고객 정보 대량 입력</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/tx/regiser/cus', d=>{
				alert( '테이블 수정 성공 여부: ' + d.msg )
			})
		})
		$('<a>전체 고객 정보 삭제</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/tx/truncate/cus', d=>{
				alert( '테이블 수정 성공 여부: ' + d.msg )
			})
		})
	}

	let comm_mgmt =()=>{
		$('#right').empty()
		$('<a>커뮤니티 테이블  생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/articles/create/table', d=>{
				alert( '커뮤니티 테이블 생성 성공 여부 : ' + d.msg )
			})
		})
		$('<a>게시글 대량 입력</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/tx/write/arts', d=>{
				alert( '게시글 입력 성공 여부: ' + d.msg )
			})
		})
	}
	let accnt_mgmt=()=>{
		$('#right').empty()
		$('<a>계좌번호 테이블 생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/account/create/table', d=>{
				alert( '테이블 생성 성공 여부 : ' + d.msg )
			})
		})
		
		$('<a>계좌번호 테이블 삭제</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			
		})
	}
	let acchis_mgmt=()=>{
		$('#right').empty()
		$('<a>계좌번호이력 테이블 생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/accountHistory/create/table', d=>{
				alert( '테이블 생성 성공 여부 : ' + d.msg )
			})
		})
	}
	let trd_mgmt=()=>{
		$('#right').empty()
	}
	let exr_mgmt=()=>{
		$('#right').empty()
		$('<a>환율 테이블 생성</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/exrates/create/table', d=>{
				alert( '테이블 생성 성공 여부 : ' + d.msg )
			})
		})
		$('<a>환율 테이블 삭제</a></br></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/exrates/delete/table', d=>{
				alert( '테이블 삭제 성공 여부 : ' + d.msg )
			})
		})//
		$('<form id="exrate_form" action="">'+
			'  <select name="exrate" size="1" >'+	//	multiple
			'  </select>'+
			'  <br>'+
			'  <input class="form-control mr-sm-2" value="국가코드 선택" type="text" placeholder="exrate" aria-label="Search">'+
			'</form>')
		.addClass('form-inline my-2 my-lg-0')
		.appendTo('#right')
		$('#exrate_form').css({padding : '0 auto', 'padding-top' : '5%'  })	//'padding-top' : '5%' 
		$('#exrate_form select').css({ 'margin-left' : '20%' , 'margin-right' : '1%'})
		
		$.each(['usd', 'cny', 'jpy', 'eur'], (i, j)=>{
			$('<option value="'+ j +'">'+ j.toUpperCase() +'</option>')
			.appendTo('#exrate_form select')
		})
		
		$('<button class="btn btn-outline-success my-2 my-sm-0" type="submit">저장</button>')
		.appendTo('#exrate_form')
		.click(e=>{
			e.preventDefault()		
		//	alert('전송 확인')
			let country = $('form#exrate_form select[name="exrate"]').val()
			
		//	if(	!$.fn.nullChecker(arr) ){			
		//		alert(arr[0] + ', '+ arr[1])
				$.getJSON( _+ '/tx/write/exrate/' + country, d=>{		// form 태그의 id란 뜻
					alert(d.exrateCount)
				})
	//		}
		})/*
		$('<a>달러 환율 데이터 입력</a></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/tx/write/exrate', d=>{
				alert( '환율 데이터 입력 성공 여부: ' + d.msg )
			})
		})
		$('<a>위안 환율 데이터 입력</a></br>')
		.appendTo('#right')
		.click(e=>{
			e.preventDefault()
			$.getJSON(_+'/tx/write/exrate', d=>{
				alert( '환율 데이터 입력 성공 여부: ' + d.msg )
			})
		})*/
	}
	return {onCreate}
})()