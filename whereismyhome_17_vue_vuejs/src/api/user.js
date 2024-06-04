import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function registerUser(param, success, fail) {
  await local.post(`/user/join`, param).then(success).catch(fail);
}

async function modifyUserInfo(param, success, fail) {
  console.log(param)
  await local.put(`/user/modify`, param).then(success).catch(fail);
}

function findPasswordById(param, success, fail) {
  console.log(param)
  local.get("/apt/findPassword", { params: param }).then(success).catch(fail);
}
export { userConfirm, findById, tokenRegeneration, logout, registerUser, modifyUserInfo, findPasswordById };
