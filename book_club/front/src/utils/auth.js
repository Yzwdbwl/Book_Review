import {useUserStore} from "@/stores/userInfo";

const userStore = useUserStore()
export const authShow = (name) => {
    console.log(name)
    for (let i = 0; i < name.length; i++) {
        console.log(name[i])
        if (userStore.user.authentication.principal.authorityList.indexOf(name[i]) !== -1) {
            return true
        }
    }
    return false


}
