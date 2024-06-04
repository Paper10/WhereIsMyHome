///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
let date = new Date();

let yearEl = document.querySelector("#year");
let yearOpt = `<option value="">매매년도선택</option>`;
let year = date.getFullYear();
for (let i = year; i > year - 20; i--) {
	yearOpt += `<option value="${i}">${i}년</option>`;
}
yearEl.innerHTML = yearOpt;

// 브라우저가 열리면 시도정보 얻기.
sendRequest("sido", "*00000000");

document.querySelector("#year").addEventListener("change", function() {
	let month = date.getMonth() + 1;
	let monthEl = document.querySelector("#month");
	let monthOpt = `<option value="">매매월선택</option>`;
	let yearSel = document.querySelector("#year");
	let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
	for (let i = 1; i < m; i++) {
		monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
	}
	monthEl.innerHTML = monthOpt;
});

// https://juso.dev/docs/reg-code-api/
// let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
// let regcode = "*00000000";
// 전국 특별/광역시, 도
// https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function() {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
		sendRequest("gugun", regcode);
	} else {
		initOption("gugun");
		initOption("dong");
	}
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function() {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
		sendRequest("dong", regcode);
	} else {
		initOption("dong");
	}
});

function sendRequest(selid, regcode) {
	const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
	let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
	fetch(`${url}?${params}`)
		.then((response) => response.json())
		.then((data) => addOption(selid, data));
}

function addOption(selid, data) {
	let opt = ``;
	initOption(selid);
	switch (selid) {
		case "sido":
			opt += `<option value="">시도선택</option>`;
			data.regcodes.forEach(function(regcode) {
				opt += `
                <option value="${regcode.code}">${regcode.name}</option>
                `;
			});
			break;
		case "gugun":
			opt += `<option value="">구군선택</option>`;
			for (let i = 0; i < data.regcodes.length; i++) {
				if (i != data.regcodes.length - 1) {
					if (
						data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
						data.regcodes[i].name.split(" ").length !=
						data.regcodes[i + 1].name.split(" ").length
					) {
						data.regcodes.splice(i, 1);
						i--;
					}
				}
			}
			let name = "";
			data.regcodes.forEach(function(regcode) {
				if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
				else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
				opt += `
                <option value="${regcode.code}">${name}</option>
                `;
			});
			break;
		case "dong":
			opt += `<option value="">동선택</option>`;
			let idx = 2;
			data.regcodes.forEach(function(regcode) {
				if (regcode.name.split(" ").length != 3) idx = 3;
				opt += `
                <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
                `;
			});
	}
	document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
	// 옵션을 초기화하는 대신, 옵션을 유지하고 빈 값으로 설정합니다.
	document.querySelector(`#${selid}`).value = "";
}


///////////////////////// 아파트 매매 정보 /////////////////////////
document.querySelector("#list-btn").addEventListener("click", function() {
	var selform = document.selform;
	let url =
		"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
	let gugunSel = document.querySelector("#gugun");
	let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
	let dongSel = document.querySelector("#dong");
	let dong = dongSel[dongSel.selectedIndex];

	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "dongCode");
	input.setAttribute("value", dong.value);

	selform.submit();

});

function makeList(data) {
	document.querySelector("table").setAttribute("style", "display: ;");
	let tbody = document.querySelector("#aptlist");
	let parser = new DOMParser();
	const xml = parser.parseFromString(data, "application/xml");
	// console.log(xml);
	initTable();
	let apts = xml.querySelectorAll("item");
	console.log("개수 : " + apts.length);
	var e = document.getElementById('dong');

	apts.forEach((apt) => {

		if (apt.querySelector("법정동").textContent.trim() !== e.options[e.selectedIndex].text.trim()) {
			return;
		}
		let tr = document.createElement("tr");

		let nameTd = document.createElement("td");
		nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
		tr.appendChild(nameTd);

		let floorTd = document.createElement("td");
		floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
		tr.appendChild(floorTd);

		let areaTd = document.createElement("td");
		areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
		tr.appendChild(areaTd);

		let dongTd = document.createElement("td");
		dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
		tr.appendChild(dongTd);

		let priceTd = document.createElement("td");
		priceTd.appendChild(
			document.createTextNode(apt.querySelector("거래금액").textContent + "만원")
		);
		priceTd.classList.add("text-end");
		tr.appendChild(priceTd);

		let address =
			apt.querySelector("중개사소재지").textContent +
			" " +
			apt.querySelector("법정동").textContent +
			" " +
			apt.querySelector("지번").textContent;
		tr.addEventListener("click", () =>
			viewMap(apt.querySelector("아파트").textContent, address)
		);

		tbody.appendChild(tr);
	});
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		level: 3, // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

var marker, infowindow;
function viewMap(apt, address) {
	if (marker != null && infowindow != null) {
		marker.setMap(null);
		infowindow.close();
	}
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(address, function(result, status) {
		// 정상적으로 검색이 완료됐으면
		if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});
			// 인포윈도우로 장소에 대한 설명을 표시합니다
			infowindow = new kakao.maps.InfoWindow({
				content: `<div style="width:150px;text-align:center;padding:6px 0;">${apt}</div>`,
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});
}

let recentmarker = null;
let recentinfo = null;

function view(ay, ax) {
	var xhr = new XMLHttpRequest();
	var sendStr = ax + " " + ay;
	xhr.open("POST", "/apt/subway");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var receivedText = xhr.responseText;
			var coords = new kakao.maps.LatLng(Number(ay), Number(ax));
			if(recentmarker!=null){
				recentmarker.setMap(null);
				recentinfo.close();
			}
			marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});

			var iwContent = '<div style="padding:5px;">'+receivedText+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

			var infowindow = new kakao.maps.InfoWindow({
				position: iwPosition,
				content: iwContent
			});

			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
			infowindow.open(map, marker);
			recentmarker = marker;
			recentinfo = infowindow;
			map.setCenter(coords);
		}
	};
	xhr.send(sendStr);

}
function initTable() {
	let tbody = document.querySelector("#aptlist");
	let len = tbody.rows.length;
	for (let i = len - 1; i >= 0; i--) {
		tbody.deleteRow(i);
	}
}