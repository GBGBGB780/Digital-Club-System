import request from '@/utils/request'


export function managerlogin(data) {
  return request({
    url: '/group/login',
    method: 'post',
    data // {name=xx,xx=xx}
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
    url: '/group/logout',
    method: 'post'
  })
}
