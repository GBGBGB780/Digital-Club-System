import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user
  },
  getters,
  state:{
		clubid:0,
    clubname:"",
	},
  // Mutation 用户变更Store数据
  mutations:{
      chooseclub(state, cid){
        state.clubid = cid
      },
      clubname(state,cname){
        state.clubname = cname
      }
  }
})

export default store
