<template>
    <form @submit.prevent="register">
    <div v-if="apt">
      <label for="userId">경매 정보 작성</label>

     <div>
        <p v-for="(value, key) in apt" :key="key">
            {{ key }}: {{ value }} {{ typeof value }}
        </p>
    </div> 
    <template v-if="Date.parse(apt.expireDate) > nDate">
    <div>
      <label for="price">추가 입찰 가격</label>
      <input type="number" id="price" v-model="trade.price" required />
    </div>
    <button type="submit">입찰</button>
    </template>
    </div>
  </form>

</template>

<script setup>
import { ref, onMounted } from "vue";
import { registUserTrade } from "@/api/apt";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router"
import { defineEmits, defineProps } from "vue"

const router = useRouter()
const nDate = new Date()
const props = defineProps({
  apt: Object,
});
const emit = defineEmits(["tradeRegistered"]);

const trade = ref({
  price: 0,
});

const register = async () => {
  const memberStore = useMemberStore();
  const { userInfo } = memberStore;
  if (userInfo.userId === props.apt.userId) {
    alert("현재 최고 금액 입찰자는 더 이상 입찰할 수 없습니다!!")
  }
  else{
    trade.value["houseInfoId"] = props.apt.houseInfoId;
    trade.value["houseTradeId"] = props.apt.houseTradeId;
    trade.value["userId"] = userInfo.userId;
    trade.value.price += props.apt.currentPrice;
    await registUserTrade(trade.value);
    emit("tradeRegistered", trade);
    props.apt = {};
    router.replace("/");
  }
};

</script>

<style scoped></style>