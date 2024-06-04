<script setup>
import { ref, watch, computed } from "vue"
import { useRouter } from "vue-router"
import { useMemberStore } from "@/stores/member"
import { registerUser } from "@/api/user"
const memberStore = useMemberStore();
const { userInfo, modifyUser, getUserInfo } = memberStore;
const router = useRouter()

getUserInfo()
const user = ref({
    userId: userInfo.userId,
    userName: userInfo.userName,
    userPwd: '',
    emailDomain: userInfo.emailDomain,
})

const modify = async () => {
    await modifyUser(user.value)
    getUserInfo()
    router.replace("mypage")
}
</script>


<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <h2 class="my-3 py-3 shadow-sm bg-light text-center">
                    <mark class="orange">정보수정</mark>
                </h2>
            </div>
            <div class="col-lg-10 text-start">
                <form @submit.prevent="modify">
                    <div>
                        <label for="userId">아이디</label>
                        <input type="text" id="userId" v-model="user.userId" disabled>
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
                    <button type="submit">수정</button>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
