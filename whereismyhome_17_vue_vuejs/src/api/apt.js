import { localAxios } from "@/util/http-commons";
const local = localAxios();

function listApts(param, success, fail) {
    console.log(param)
    local.get("/apt/tradelist", { params: param }).then(success).catch(fail);
  }

  function listTradeLog(param, success, fail) {
    console.log(param)
    local.get("/apt/list", { params: param }).then(success).catch(fail);
  }
  
  function listAptsByDong(param, success, fail) {
    local.get("/apt/infolist", { params: param }).then(success).catch(fail);
  }

  function registTrade(param, success, fail) {
    console.log(param);
    local.post("/trade/regist", param).then(success).catch(fail);
  }

  function registUserTrade(param, success, fail) {
    console.log(param);
    local.post("/trade/updateprice", param).then(success).catch(fail);
  }

  function listUserTrade(param, success, fail) {
    console.log(param);
    local.get("/trade/mypage", { params: param }).then(success).catch(fail);
  }

  export { listApts, listAptsByDong, registTrade, registUserTrade, listUserTrade, listTradeLog };
  