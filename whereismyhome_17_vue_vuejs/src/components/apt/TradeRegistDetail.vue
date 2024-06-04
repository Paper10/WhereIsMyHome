<template>
    <form @submit.prevent="register">
    <div v-if="apt">
      <label for="userId">경매 정보 작성</label>

     <div>
        <p v-for="(value, key) in apt" :key="key">
            {{ key }}: {{ value }}
        </p>
    </div> 
    <div>
      <label for="price">시작 가격</label>
      <input type="number" id="price" v-model="trade.currentPrice" required />
    </div>
    <div>
      <label for="expireDate">종료 날짜</label>
      <input type="date" id="expireDate" v-model="trade.expireDate" required />
    </div>
    <button type="submit">가입</button>
    </div>
  </form>

</template>

<script setup>
import { ref, onMounted } from "vue";
import { registTrade } from "@/api/apt";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router"

const router = useRouter()

const props = defineProps({
  apt: Object,
});

const trade = ref({
  currentPrice: 0,
  expireDate: "",
});

const register = async () => {
  const memberStore = useMemberStore();
  const { userInfo } = memberStore;
  trade.value["houseInfoId"] = props.apt.houseInfoId;
  trade.value["userId"] = userInfo.userId;
  console.log(trade.value["userId"]);
  await registTrade(trade.value);
  router.replace("/");
};

</script>

<style scoped></style>