<script setup>
import { ref } from 'vue'
import KakaoMap from '@/components/apt/KakaoMap.vue'
import RecentTradeList from '@/components/apt/RecentTradeList.vue'
import RecentTradeDetail from '@/components/apt/RecentTradeDetail.vue'
const apt = ref(null)
const setApt = (eapt) => {
  apt.value = { ...eapt }
}
</script>

<template>
  <main>
    <h1>거래 내역</h1>
    <KakaoMap :apt="apt" />
    <RecentTradeList @emit-apt="setApt" />
    <RecentTradeDetail :apt="apt" />
  </main>
</template>

<style scoped></style>