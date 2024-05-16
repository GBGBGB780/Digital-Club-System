import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data: data
  })
}

export function register(data) {
  console.error(data)
  return request({
    url: '/user/register',
    method: 'post',
    data: data
  })
}

export function modifypassword(stunumber, password) {
  return request({
      url: '/user/modifypass',
      method: 'post',
      params: { stunumber, password }
  })
}

export function modifyphone(stunumber, phone) {
  return request({
      url: '/user/modifyphone',
      method: 'post',
      params: { stunumber, phone }
  })
}

export function modifydescription(stunumber, description) {
  return request({
      url: '/user/modifydescription',
      method: 'post',
      params: { stunumber, description }
  })
}

export function modifynickname(stunumber, nickname) {
  return request({
      url: '/user/modifynickname',
      method: 'post',
      params: { stunumber, nickname }
  })
}

export function validateEmail(email) {
  console.error({ email })
  return request({
      url: '/user/validateEmail',
      method: 'post',
      params: { email }
  })
}

export function getProfile(stunumber) {
  return request({
    url: '/user/profile',
    method: 'post',
    params: { stunumber }
  })
}


export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token } // name=xx&xxx=xxx
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
