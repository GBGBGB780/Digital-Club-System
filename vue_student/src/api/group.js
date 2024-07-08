import request from '@/utils/request'

export function getGroups(searchInfo) {
  return request({
    url: '/group/all',
    method: 'get',
    params: { searchInfo }
  })
}

export function getVideo() {
  return request({
    url: '/group/getVideo',
    method: 'get',
    params: { }
  })
}

export function getWeather() {
  return request({
    url: '/getWeather',
    method: 'get',
    params: { }
  })
}

export function getTopGroups() {
  console.error('!!!!!')
  return request({
    url: '/group/top',
    method: 'get',
    params: { }
  })
}

export function getApplications(stuNumber) {
  return request({
    url: '/application/myApps',
    method: 'post',
    params: { stuNumber }
  })
}

export function postform(form) {
  return request({
    url: '/application/submit',
    method: 'post',
    data: form
  })
}

export function getAttachmentUrl(groupId) {
  return request({
    url: '/group/getAttachment',
    method: 'post',
    params: { id: groupId }
  })
}

export function showGroups(searchInfo) {
  return request({
    url: '/group/all',
    method: 'post',
    params: { searchInfo }
  })
}

export function getMyGroups(studentId) {
  return request({
    url: '/individualGroup/allGroups',
    method: 'post',
    params: { studentId }
  })
}

