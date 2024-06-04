<script setup>
import { ref, onMounted } from "vue"
import { listUserTrade } from "@/api/apt";
import AptDetail from '@/components/apt/AptDetail.vue'
import { useMemberStore } from "@/stores/member";


const nDate = new Date()
const apts = ref([])
onMounted(() => {
    const memberStore = useMemberStore();
    const { userInfo } = memberStore;
    const userId = userInfo.userId;
    listUserTrade(
        { userId },
        ({ data }) => {
            console.log(data)
            apts.value = data;
        },
        (err) => {
            console.log(err);
        }
    );
})



// apts.value = [
//     {
//         id: 0,
//         userId: 1,
//         price: 10,
//         exDate: new Date(2012, 11, 12),
//     },
//     {
//         id: 1,
//         userId: 1,
//         price: 10000,
//         exDate: new Date(2032, 11, 12),
//     },
//     {
//         id: 2,
//         userId: 1,
//         price: 22000,
//         exDate: new Date(2032, 1, 1),
//     },
//     {
//         id: 3,
//         userId: 1,
//         price: 33300,
//         exDate: new Date(2025, 7, 17),
//     },
// ]



</script>

<template>
    <div v-for="apt in apts" :key="apt.tradeInfoId">
        <AptDetail :apt="apt" />
        <!-- <template v-if="apt.exDate > nDate">
            진행중
        </template>
        <template v-else>
            낙찰됨
        </template> -->
        <hr>
    </div>


</template>

<style scoped></style>