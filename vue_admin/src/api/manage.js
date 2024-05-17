import request from '@/utils/request'

export function getManages(groupname) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupname }
  })
}

export function getDetails(groupname) {
  return request({
    url: '/group/detail',
    method: 'post',
    params: { groupname }
  })
}

export function redirectToGroupDetail(groupname) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupname }
  })
}

export function updateForm(groupname, description, attachment, image) {
  return request({
    url: '/group/modifydescription',
    method: 'post',
    params: { groupname, description, attachment, image }
  })
}

export function updatePassword(groupname, password) {
  return request({
    url: '/group/modifypassword',
    method: 'post',
    params: { groupname, password }
  })
}
