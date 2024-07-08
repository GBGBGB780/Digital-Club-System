import request from '@/utils/request'

export function getManages(groupName) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupName }
  })
}

export function getDetails(groupname) {
  return request({
    url: '/group/managerDetail',
    method: 'post',
    params: { groupname }
  })
}

export function redirectToGroupDetail(groupName) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupName }
  })
}

export function updateForm(groupname, description, attachment,image) {
  return request({
    url: '/group/modifydescription',
    method: 'post',
    params: { groupname, description, attachment, image }
  })
}

export function updatePassword(groupName, password) {
  return request({
    url: '/group/modifyPassword',
    method: 'post',
    params: { groupName, password }
  })
}
