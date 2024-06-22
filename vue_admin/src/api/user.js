import request from '@/utils/request'


export function managerlogin(userId,password) {
  return request({
    url: '/manager/login',
    method: 'post',
    params: { userId,password }
  })
}

export function getInfo(token) {
  return request({
    url: '/manager/info',
    method: 'get',
    params: { token } // name=xx&xxx=xxx
  })
}

export function logout() {
  return request({
    url: '/manager/logout',
    method: 'post'
  })
}

  export function managedgroup(managerId) {
    return request({
      url: '/individualGroup/allManagedGroups',
      method: 'get',
      params: { managerId }
    })
}
