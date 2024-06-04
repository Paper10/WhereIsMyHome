<template>
    <form @submit.prevent="register">
    <div v-if="apt">
      <label for="userId">경매 정보 작성</label>

     <div>
        <p v-for="(value, key) in apt" :key="key">
            {{ key }}: {{ value }} {{ typeof value }}
        </p>
    </div> 
    </div>
  </form>

</template>

<script setup>
import { defineProps } from "vue"

const props = defineProps({
  apt: Object,
});


</script>

<style scoped></style>