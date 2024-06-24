import request from '@/utils/request'

export function login(userId, password) {
  return request({
    url: '/topManager/login',
    method: 'post',
    params: { userId, password }
  })
}

export function getInfo(token) {
  return request({
    url: '/topManager/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/topManager/logout',
    method: 'post'
  })
}
export function getAllStudent(searchInfo) {
  return request({
    url: '/topManager/getAllUsers',
    method: 'post',
    params: { searchInfo }
  })
}
export function showGroups(searchInfo) {
  return request({
    url: '/group/all',
    method: 'post',
    params: { searchInfo }
  })
}

export function getGroups(searchInfo) {
  return request({
    url: '/group/all',
    method: 'get',
    params: { searchInfo }
  })
}
