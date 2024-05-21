import request from '@/utils/request'

export function login(stunumber,password) {
  return request({
    url: '/student/login',
    method: 'post',
    params: { stunumber, password }
  })
}

export function register(data) {
  console.error(data)
  return request({
    url: '/student/register',
    method: 'post',
    data: data
  })
}

export function modifypassword(stunumber, password) {
  return request({
      url: '/student/modifypass',
      method: 'post',
      params: { stunumber, password }
  })
}

export function modifyphone(stunumber, phone) {
  return request({
      url: '/student/modifyphone',
      method: 'post',
      params: { stunumber, phone }
  })
}

export function modifydescription(stunumber, description) {
  return request({
      url: '/student/modifydescription',
      method: 'post',
      params: { stunumber, description }
  })
}

export function modifynickname(stunumber, nickname) {
  return request({
      url: '/student/modifynickname',
      method: 'post',
      params: { stunumber, nickname }
  })
}

export function validateEmail(email) {
  console.error({ email })
  return request({
      url: '/student/validateEmail',
      method: 'post',
      params: { email }
  })
}

export function getProfile(stunumber) {
  return request({
    url: '/student/profile',
    method: 'post',
    params: { stunumber }
  })
}


export function getInfo(token) {
  return request({
    url: '/student/info',
    method: 'get',
    params: { token } // name=xx&xxx=xxx
  })
}

export function logout() {
  return request({
    url: '/student/logout',
    method: 'post'
  })
}
