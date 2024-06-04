<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { ref, computed, onMounted } from "vue";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const { userLogout } = memberStore;
const userInfo = computed(() => memberStore.userInfo);

const logout = () => {
  userLogout();
};

// onMounted(() => {
//   if(userInfo){
//     // console.log(userinfo.value);
//     const memberStore = useMemberStore();
//     const { reloadUser } = memberStore;
//     reloadUser();
//   }
// });
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
    <div class="container-fluid">
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        <img
          src="@/assets/ssafy_logo.png"
          class="rounded mx-auto d-block"
          alt="..."
        />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarScroll"
        aria-controls="navbarScroll"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <ul
          class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <li class="nav-item">
            <router-link :to="{ name: 'main' }" class="nav-link">경매정보</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'apt-recentTrade' }" class="nav-link">거래내역</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'board' }" class="nav-link">QnA</router-link>
          </li>
        </ul>
        <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link
                    to="/"
                    @click.prevent="logout"
                    class="nav-link"
                    >{{ menu.name }}</router-link>
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <router-link
                    :to="{ name: menu.routeName }"
                    class="nav-link"
                    >{{ menu.name }}</router-link>
                </li>
              </template>
            </template>
          </template>
          <template v-if="userInfo && userInfo.isAdmin === '1'">
            <li class="nav-item">
              <router-link
                :to="{ name: 'apt-tradeRegist' }"
                class="nav-link"
                >거래등록</router-link>
            </li>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
