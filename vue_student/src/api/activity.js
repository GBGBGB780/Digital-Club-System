import request from '@/utils/request'

export function getActivites(searchinfo) {
    return request({
      url: '/activity/all',
      method: 'get',
      params: { searchinfo }
    })
  }

  export function getActivitesDetail() {
    console.error('!!!!!')
    return request({
      url: '/activity/studentDetail',
      method: 'get',
      params: { }
    })
  }

  export function getTopActivites() {
    console.log('!!!!!')
    return request({
      url: '/activity/top',
      method: 'get',
      params: { }
    })
  }