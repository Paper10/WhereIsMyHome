<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"
import { useMemberStore } from "@/stores/member"
import { registerUser } from "@/api/user"

const router = useRouter()
const memberStore = useMemberStore()

const { userLogin, getUserInfo, registUser } = memberStore

const user = ref({
  userId: '',
  userName: '',
  userPwd: '',
  emailDomain: '',
})

const register = async () => {
  await registUser(user.value)
  router.replace("login")
}
</script>


<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form @submit.prevent="register">
          <div>
            <label for="userId">아이디</label>
            <input type="text" id="userId" v-model="user.userId" required>
          </div>
          <div>
            <label for="userName">이름</label>
            <input type="text" id="userName" v-model="user.userName" required>
          </div>
          <div>
            <label for="userPwd">비밀번호</label>
            <input type="password" id="userPwd" v-model="user.userPwd" required>
          </div>
          <div>
            <label for="emailDomain">이메일 도메인</label>
            <input type="email" id="emailDomain" v-model="user.emailDomain" required>
          </div>
          <button type="submit">가입</button>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
