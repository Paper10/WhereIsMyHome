<script setup>
import { ref, onMounted } from "vue";
import { listStations } from "@/api/estation";
import { listSido, listGugun, listDong } from "@/api/map";
import { listApts } from "@/api/apt";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

  // const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_OPEN_API_SERVICE_KEY,
  VITE_CHARGING_OPEN_API_SERVICE_KEY } = import.meta.env;

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const dongList = ref([{ text: "동선택", value:""}]);
const chargingStations = ref([]);
const selectStation = ref({});

const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  zscode: 0,
});
const paramElectricCharge = ref({
  metroCd: 11,
  apiKey: VITE_CHARGING_OPEN_API_SERVICE_KEY,
});
const paramListApt = ref([]);
onMounted(() => {
  getChargingStations();
  getSidoList();
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.dongCode });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  listGugun(
    { sido: val },
    ({ data }) => {
      let options = [];
      options.push({ text: "구군선택", value: "" });
      data.forEach((gugun) => {
        options.push({ text: gugun.gugunName, value: gugun.dongCode });
      });
      gugunList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeGugun = (val) => {
  param.value.zscode = val;
  listDong(
    { gugun: val },
    ({ data }) => {
      let options = [];
      options.push({ text: "동선택", value: "" });
      data.forEach((dong) => {
        options.push({ text: dong.dongName, value: dong.dongCode });
      });
      dongList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};
const onChangeDong = (val) => {
  param.value.zscode = val;
  getListApts(val);
};
const getListApts = (dongCode) => {
  listApts(
    {dongCode},
    ({ data }) => {
      paramListApt.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
};
const getChargingStations = () => {
  listStations(
    paramElectricCharge.value,
    ({ data }) => {
      chargingStations.value = data.items[0].item;
    },
    (err) => {
      console.log(err);
    }
  );
};

// const viewStation = (station) => {
//   selectStation.value = station;
// };
</script>

<template>
  <div class="container text-center mt-3">
    <div class="alert alert-success" role="alert">아파트 리스트</div>
    <div class="row mb-2">
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
      </div>
      <div class="col"><VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" /></div>
      <div class="col"><VSelect :selectOption="dongList" @onKeySelect="onChangeDong" /></div>
    </div>
    <!-- <VKakaoMap :stations="chargingStations" :selectStation="selectStation" /> -->
    <table class="table table-hover">
      <thead>
        <tr class="text-center">
          <th scope="col">아파트명</th>
          <th scope="col">아파트ID</th>
          <th scope="col">면적</th>
          <th scope="col">동</th>
          <th scope="col">가격</th>
          <th scope="col">위도</th>
          <th scope="col">위도</th>
        </tr>
      </thead>
      <tbody>
       
        <tr
          class="text-center"
          v-for="apt in paramListApt"
          :key="apt.apartmentName"
        >
         <!-- hi.buildYear,
         hi.roadName,
          hi.dong,
           hi.apartmentName,
            hi.jibun,
             ht.current_price,
              ht.expire_date 
               -->
          <th> {{  apt.apartmentName }}</th>
          <td>{{ apt.buildYear }}</td>
          <td>{{ apt.roadName  }}</td> 
          <td>{{ apt.apartmentName }}</td>
          <td>{{ apt.jibun }}</td>
          <td>{{ apt.currentPrice }}</td>
          <td>{{ apt.expireDate }}</td>  
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
</style>
