import request from '@/utils/request'

export function login(stuNumber,password) {
  return request({
    url: '/student/login',
    method: 'post',
    params: { stuNumber, password }
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

export function modifypassword(stuNumber, password) {
  return request({
      url: '/student/modifypass',
      method: 'post',
      params: { stuNumber, password }
  })
}

export function modifyphone(stuNumber, phone) {
  return request({
      url: '/student/modifyphone',
      method: 'post',
      params: { stuNumber, phone }
  })
}

export function modifydescription(stuNumber, description) {
  return request({
      url: '/student/modifydescription',
      method: 'post',
      params: { stuNumber, description }
  })
}

export function modifynickname(stuNumber, nickname) {
  return request({
      url: '/student/modifynickname',
      method: 'post',
      params: { stuNumber, nickname }
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

export function getProfile(stuNumber) {
  return request({
    url: '/student/profile',
    method: 'post',
    params: { stuNumber }
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
