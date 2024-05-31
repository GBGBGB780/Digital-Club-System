import request from '@/utils/request'

export function getGroups(searchinfo) {
  return request({
    url: '/group/all',
    method: 'get',
    params: { searchinfo }
  })
}

export function getVideo() {
  return request({
    url: '/group/getvideo',
    method: 'get',
    params: { }
  })
}

export function getWeather() {
  return request({
    url: '/getweather',
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
    url: '/application/myapps',
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
    url: '/group/getattachment',
    method: 'post',
    params: { id: groupId }
  })
}

