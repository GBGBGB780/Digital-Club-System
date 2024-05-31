import request from '@/utils/request'

export function getManages(groupName) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupName }
  })
}

export function getDetails(groupName) {
  return request({
    url: '/group/detail',
    method: 'post',
    params: { groupName }
  })
}

export function redirectToGroupDetail(groupName) {
  return request({
    url: '/group/myclub',
    method: 'post',
    params: { groupName }
  })
}

export function updateForm(groupName, description, attachment, image) {
  return request({
    url: '/group/modifyDescription',
    method: 'post',
    params: { groupName, description, attachment, image }
  })
}

export function updatePassword(groupName, password) {
  return request({
    url: '/group/modifyPassword',
    method: 'post',
    params: { groupName, password }
  })
}
