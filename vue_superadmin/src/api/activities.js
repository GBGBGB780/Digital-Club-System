import request from '@/utils/request'

export function searchActivities(searchInfo) {
  return request({
    url: '/activity/all',
    method: 'post',
    params: { searchInfo }
  })
}

export function getManangerDetails(groupName, activityName) {
  return request({
    url: '/activity/managerDetail',
    method: 'post',
    params: { groupName, activityName }
  })
}

export function modDesc(groupName, activityName, description, attachment, image) {
  return request({
    url: '/activity/modifyDescription',
    method: 'post',
    params: { groupName, activityName, description, attachment, image }
  })
}

export function modifyInfo(activity) {
  return request({
    url: '/activity/modifyInfo',
    method: 'post',
    params: { activity }
  })
}

export function addActivity(activity) {
  return request({
    url: '/activity/addActivity',
    method: 'post',
    params: { activity }
  })
}

export function acceptActivity(activityId) {
  return request({
    url: '/activity/accept',
    method: 'post',
    params: { activityId }
  })
}

export function rejectActivity(activityId) {
  return request({
    url: '/activity/reject',
    method: 'post',
    params: { activityId }
  })
}

// super admin
export function getAllApps() {
  return request({
    url: '/activity/allApps',
    method: 'post'
  })
}

export function getAppDetail(groupName, activityName) {
  return request({
    url: '/activity/appDetail',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    params: { groupName, activityName }
  })
}