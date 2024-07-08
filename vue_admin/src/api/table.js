import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}

export function getStudentList(groupId) {
  return request({
    url: '/individualGroup/getStudentsByGroup',
    method: 'post',
    params: { groupId }
  })
}