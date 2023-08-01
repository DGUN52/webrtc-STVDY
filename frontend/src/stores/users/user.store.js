import { defineStore } from 'pinia'
import { fetchWrapper } from '@/helpers'
import { useAuthStore } from '@/stores'
import { getUser } from '@/api/user'
import router from '@/router'
import jwtDecode from 'jwt-decode'

const baseUrl = `${import.meta.env.VITE_API_URL}/users`

export const useUsersStore = defineStore({
  id: 'users',
  state: () => ({
    users: {},
    user: {},
  }),
  actions: {
    async getInfo(token) {
      const AuthStore = useAuthStore()

      await getUser(
        async (data) => {
          this.user = {
            email: data.data.email,
            realname: data.data.name,
            username: data.data.nickname,
          }

          await AuthStore.setValid(true)
        },
        async (error) => {
          console.log(error)

          await AuthStore.setValid(false)
        },
      )
    },

    async varificationEmail(email) {
      await fetchWrapper.get(`${baseUrl}/varifyemail`, email)
      // baseUrl/users/register/{email}
    },

    async getAll() {
      this.users = { loading: true }
      try {
        this.users = await fetchWrapper.get(baseUrl)
      } catch (error) {
        this.users = { error }
      }
    },
    async getById(id) {
      this.user = { loading: true }
      try {
        this.user = await fetchWrapper.get(`${baseUrl}/${id}`)
      } catch (error) {
        this.user = { error }
      }
    },
    async update(id, params) {
      await fetchWrapper.put(`${baseUrl}/${id}`, params)

      // update stored user if the logged in user updated their own record
      const authStore = useAuthStore()
      if (id === authStore.user.id) {
        // update local storage
        const user = { ...authStore.user, ...params }
        localStorage.setItem('user', JSON.stringify(user))

        // update auth user in pinia state
        authStore.user = user
      }
    },
    async delete(id) {
      // add isDeleting prop to user being deleted
      this.users.find((x) => x.id === id).isDeleting = true

      await fetchWrapper.delete(`${baseUrl}/${id}`)

      // remove user from list after deleted
      this.users = this.users.filter((x) => x.id !== id)

      // auto logout if the logged in user deleted their own record
      const authStore = useAuthStore()
      if (id === authStore.user.id) {
        authStore.logout()
      }
      // async register(user) {
      //   await fetchWrapper.post(`${baseUrl}/register`, user)
      //   // baseUrl/users/register/{requestBody} // success/fail로 응답받음
      // },
    },
  },
})
