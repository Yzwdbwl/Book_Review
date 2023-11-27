import request from "@/utils/request";

//系统相关
export const login = (data) => {
    return request.post('/login', data,{
        headers:{
            "Content-Type":"application/x-www-form-urlencoded;charset=UTF-8"
        }
    })
}

export const logout = (data) => {
    return request.get('/logout')
}


export  const register = (data) => {
    return request.post('/system/register', data)
}










