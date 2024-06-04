import { ref } from "vue"
import { useRouter } from "vue-router"
import { defineStore } from "pinia"
import { jwtDecode } from "jwt-decode"

import { userConfirm, findById, tokenRegeneration, logout, registerUser, modifyUserInfo, findPasswordById } from "@/api/user"
import { httpStatusCode } from "@/util/http-status"
import { useMenuStore } from "@/stores/menu"


export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter()

  const isLogin = ref(false)
  const isLoginError = ref(false)
  const userInfo = ref(null)
  const isValidToken = ref(false)

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("로그인 성공!!!!")
          let { data } = response
          let accessToken = data["access-token"]
          let refreshToken = data["refresh-token"]
          isLogin.value = true
          isLoginError.value = false
          isValidToken.value = true
          sessionStorage.setItem("accessToken", accessToken)
          sessionStorage.setItem("refreshToken", refreshToken)

          const { changeMenuState } = useMenuStore()
          getUserInfo(accessToken)
          changeMenuState()
        }
      },
      (error) => {
        console.log("로그인 실패!!!!")
        isLogin.value = false
        isLoginError.value = true
        isValidToken.value = false
        console.error(error)
      }
    )
  }

  const registUser = async (memberData) => {
    await registerUser(
      memberData,
      (response) => {
        if (response.status === httpStatusCode.CREATE) { // 사용자 등록이 성공적으로 이루어졌을 때
          console.log("사용자 등록 성공");
          // 사용자 등록 후 추가적인 작업이 필요한 경우 여기에 추가
        } else {
          console.error("사용자 등록 실패:", response.statusText);
          // 실패한 경우에 대한 처리
        }
      },
      (error) => {
        console.log(memberData);
        console.error("사용자 등록 요청 실패:", error);
        // 오류가 발생한 경우에 대한 처리
      }
    )
  }

  const modifyUser = async (memberData) => {
    await modifyUserInfo(
      memberData,
      (response) => {
        if (response.status === httpStatusCode.CREATE) { // 사용자 등록이 성공적으로 이루어졌을 때
          console.log("사용자 수정 성공");
          // 사용자 등록 후 추가적인 작업이 필요한 경우 여기에 추가
        } else {
          console.error("사용자 수정 실패:", response.statusText);
          // 실패한 경우에 대한 처리
        }
      },
      (error) => {
        console.log(memberData);
        console.error("사용자 수정 요청 실패:", error);
        // 오류가 발생한 경우에 대한 처리
      }
    )
  }

  const getUserInfo = async (token) => {
    let decodeToken = jwtDecode(token)
    console.log(decodeToken)
    await findById(
      decodeToken.userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userInfo.value = response.data.userInfo
        } else {
          console.log("유저 정보 없음!!!!")
        }
      },
      async (error) => {
        console.error(
          "g[토큰 만료되어 사용 불가능.] : ",
          error.response.status,
          error.response.statusText
        )
        isValidToken.value = false

        await tokenRegenerate()
      }
    )
  }

  const tokenRegenerate = async () => {
    await tokenRegeneration(
      JSON.stringify(userInfo.value),
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let accessToken = response.data["access-token"]
          sessionStorage.setItem("accessToken", accessToken)
          isValidToken.value = true
        }
      },
      async (error) => {
        // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
        if (error.response.status === httpStatusCode.UNAUTHORIZED) {
          // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
          await logout(
            userInfo.value.userid,
            (response) => {
              if (response.status === httpStatusCode.OK) {
                console.log("리프레시 토큰 제거 성공")
              } else {
                console.log("리프레시 토큰 제거 실패")
              }
              alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.")
              isLogin.value = false
              userInfo.value = null
              isValidToken.value = false
              router.push({ name: "user-login" })
            },
            (error) => {
              console.error(error)
              isLogin.value = false
              userInfo.value = null
            }
          )
        }
      }
    )
  }

  const userLogout = async () => {
    console.log("로그아웃 아이디 : " + userInfo.value.userId)
    await logout(
      userInfo.value.userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          isLogin.value = false
          userInfo.value = null
          isValidToken.value = false

          sessionStorage.removeItem("accessToken")
          sessionStorage.removeItem("refreshToken")

          const { changeMenuState } = useMenuStore()
          changeMenuState()
        } else {
          console.error("유저 정보 없음!!!!")
        }
      },
      (error) => {
        console.log(error)
      }
    )
  }

  const reloadUser = () => {
    const at = sessionStorage.getItem("accessToken")
    isLogin.value = true
    isLoginError.value = false
    isValidToken.value = true
    const { changeMenuState } = useMenuStore()
    getUserInfo(at)
    changeMenuState()
    console.log(at);
    console.log("user reloaded");
  }

  const findPassword = async (loginUser) => {
    
    await findPasswordById(
      loginUser.userId,
      (response) => {
        console.log(response)
      },
      async (error) => {
        console.log(error)
      }
    )
  }
  

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    userLogin,
    getUserInfo,
    tokenRegenerate,
    userLogout,
    registUser,
    modifyUser,
    reloadUser,
    findPassword,
  }
})
