import { defineStore } from 'pinia'
import { fetchWrapper } from '@/helpers'
import { useAlertStore, useUsersStore } from '@/stores'
import { loginAuth } from '@/api/auth'
import router from '@/router'

const baseUrl = `${import.meta.env.VITE_API_URL}/users`

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    // 유저가 로그인 상태를 유지할 수 있도록 로컬스토리지로부터 상태를 초기화
    isLogin: false,
    isValidToken: false,
    // user: {},
    returnUrl: null,
  }),
  actions: {
    async setValid(value) {
      this.isLogin = value
      this.isValidToken = value
    },
    async login(values) {
      const user = {
        email: values.email,
        password: values.password,
      }

      await loginAuth(
        user,
        async (data) => {
          // if (values.keeplog === true) isLogin = true

          // this.isLogin = true
          // this.isValidToken = true
          const token = data.data.token

          if (values.keeplog) localStorage.setItem('access-token', token)
          sessionStorage.setItem('access-token', token)

          const usersStore = useUsersStore()
          await usersStore.getInfo(token)

          // sessionStorage.setItem('refresh-token', refreshToken)
        },
        (error) => {
          console.log(error)

          this.setValid(false)
        },
      )
    },
    async passwordreset(username, email) {
      try {
        console.log('>>>>암호 리셋함수 도착<<<<')
        // baseUrl/users/pwdreset/{requestBody}
      } catch (error) {
        const alertStore = useAlertStore()
        alertStore.error(error)
      }
      router.push(this.returnUrl || '/')
    },
    async deactivate() {
      try {
        console.log('>>>>비활성화 함수 도착<<<<')
        console.log(this.user)
        // baseUrl/users/{id}
        this.user = null
        localStorage.removeItem('user')
        router.push('/about')
      } catch (error) {
        const alertStore = useAlertStore()
        alertStore.error(error)
      }
    },
    logout() {
      const usersStore = useUsersStore()
      usersStore.user = null

      this.setValid(false)

      localStorage.removeItem('access-token')
      sessionStorage.removeItem('access-token')
      router.push('/about')
    },
  },
})
