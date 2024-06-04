<script setup>
import { ref } from 'vue'
import KakaoMap from '@/components/apt/KakaoMap.vue'
import TradeRegistList from '@/components/apt/TradeRegistList.vue'
import TradeRegistDetail from '@/components/apt/TradeRegistDetail.vue'
const apt = ref(null)
const setApt = (eapt) => {
  apt.value = { ...eapt }
}
</script>

<template>
  <main>
    <h1>거래등록</h1>
    <KakaoMap :apt="apt" />
    <TradeRegistList @emit-apt="setApt" />
    <TradeRegistDetail :apt="apt" />
  </main>
</template>

<style scoped></style>