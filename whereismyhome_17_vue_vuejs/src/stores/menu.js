import { ref } from "vue";
import { defineStore } from "pinia";
import { useMemberStore } from "@/stores/member";



export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "회원가입", show: true, routeName: "user-join" },
    { name: "로그인", show: true, routeName: "user-login" },
    { name: "내정보", show: false, routeName: "user-mypage" },
    { name: "로그아웃", show: false, routeName: "user-logout" },
    // { name: "거래등록", show: false, routeName: "apt-trageRegist" },
  ]);

  const changeMenuState = () => {
    const memberStore = useMemberStore();
    const { isLogin } = memberStore;
    

    menuList.value = menuList.value.map((item) => {
      if (item.routeName === "user-join" || item.routeName === "user-login") {
        return { ...item, show: !isLogin };
      }
      if (item.routeName === "user-mypage" || item.routeName === "user-logout") {
        return { ...item, show: isLogin };
      }
      // if (item.routeName === "apt-tradeRegist" && isAdmin) {
      //   return { ...item, show: true };
      // }
      return item;
    });
  };
  
  return {
    menuList,
    changeMenuState,
  };
});
