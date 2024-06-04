<script setup>
import { ref } from 'vue'
import KakaoMap from '@/components/apt/KakaoMap.vue'
import AptList from '@/components/apt/AptList.vue'
import AptDetail from '@/components/apt/AptDetail.vue'
import { useMemberStore } from "@/stores/member"

const apt = ref(null)
const setApt = (eapt) => {
  apt.value = { ...eapt }
}

let aptL1 = ref(0)
let aptL2 = ref(0)
const refreshAptList = () => {
  aptL1.value += 1
  aptL2.value += 1
  apt.value = null
}
</script>

<template>
  <main>
    <KakaoMap :key="aptL1" :apt="apt" />
    <AptList :key="aptL2" @emit-apt="setApt" />
    <AptDetail :apt="apt" @trade-registered="refreshAptList"/>
  </main>
</template>

<style scoped></style>