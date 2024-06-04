<template>
    <div class="row mb-2">
        <div class="col d-flex flex-row-reverse">
            <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
        </div>
        <div class="col">
            <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
        </div>
        <div class="col">
            <VSelect :selectOption="dongList" @onKeySelect="onChangeDong" />
        </div>
    </div>

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

            <tr class="text-center" v-for="apt in paramListApt" @click="SendMarker(apt)" :key="apt.apartmentName">
                <!-- hi.buildYear,
         hi.roadName,
          hi.dong,
           hi.apartmentName,
            hi.jibun,
             ht.current_price,
              ht.expire_date 
               -->
                <th> {{ apt.apartmentName }}</th>
                <td>{{ apt.buildYear }}</td>
                <td>{{ apt.roadName }}</td>
                <td>{{ apt.apartmentName }}</td>
                <td>{{ apt.jibun }}</td>
                <td>{{ apt.currentPrice }}</td>
                <td>{{ apt.expireDate }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script setup>
import { ref, defineEmits, onMounted, defineProps, watch } from "vue";
import { listSido, listGugun, listDong } from "@/api/map";
import { listApts } from "@/api/apt";
import VSelect from "@/components/common/VSelect.vue";

const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;
const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const dongList = ref([{ text: "동선택", value: "" }]);
const emit = defineEmits(['emitApt'])
// const apts = ref([])

const props = defineProps({
  aptD: Object,
});

const param = ref({
    serviceKey: VITE_OPEN_API_SERVICE_KEY,
    pageNo: 1,
    numOfRows: 20,
    zscode: 0,
});
const paramListApt = ref([]);

onMounted(() => {
    // getChargingStations();
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
    console.log("list Update")
    listApts(
        { dongCode },
        ({ data }) => {
            paramListApt.value = data;
        },
        (err) => {
            console.log(err);
        }
    );
};

watch(
  () => props.aptD,
  () => {
    var NaptD = props.aptD
    for (let i = 0; i < paramListApt.value.length; i++){
        if(paramListApt.value[i].houseTradeId === NaptD.houseTradeId){
            console.log("ok");
            paramListApt.value[i].currentPrice = NaptD.price;
            paramListApt.value[i].userId = NaptD.userId;
            break;
        }
    }
    console.log(NaptD)
    console.log(paramListApt.value);
  }
);

const SendMarker = (apt) => {
    emit('emitApt', apt)
}

</script>

<style scoped>
mark.purple {
    background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
</style>