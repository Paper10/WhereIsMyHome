<script setup>
import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = memberStore;
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img src="https://source.unsplash.com/random/250x250/?food" class="img-fluid rounded-start" alt="..." />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item" v-for="(value, key) in userInfo" :key="key">
                    {{ key }}: {{ value }}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div>
          <router-link to="modify" class="btn btn-outline-secondary mt-2">수정</router-link>
          <router-link to="aptlist" class="btn btn-outline-secondary mt-2">내 입찰</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
