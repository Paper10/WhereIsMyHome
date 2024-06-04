import { localAxios } from "@/util/http-commons";
const local = localAxios();

function listApts(param, success, fail) {
    local.get("/apt", { params: param }).then(success).catch(fail);
  }
  
  export { listApts };
  