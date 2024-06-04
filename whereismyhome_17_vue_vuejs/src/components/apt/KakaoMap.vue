<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { toRaw } from "vue";
var markerPositions1 = [
  [33.452278, 126.567803],
  [33.452671, 126.574792],
  [33.451744, 126.572441],
]



export default {
  name: "KakaoMap",
  props: {
    apt: Object
  },
  data() {
    return {
      markerPositions1,
      markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=37e54fadcf61dad2a530215fd5209ca6";
      document.head.appendChild(script);
    }
  },
  watch: {
    apt: function (newApt, oldApt) {
      console.log(newApt)
      this.displayMarker(newApt);
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(newApt) {
      var lat = newApt.lat
      var lng = newApt.lng
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
          center: new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
          level: 3 // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      // 마커가 표시될 위치입니다 
      var markerPosition = new kakao.maps.LatLng(lat, lng);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      var iwContent = `<div style="padding:5px;">${newApt.apartmentName}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent,
        removable: true,
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker);

    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        toRaw(this.map).setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = this.map.getCenter(), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: toRaw(this.map), // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      toRaw(this.map).setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
