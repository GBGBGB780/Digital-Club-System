import request from '@/utils/request'

export function getMyActivity(groupName) {
    return request({
        url: '/activity/myActivity',
        method: 'post',
        params: { groupName }
    });
}

export function getManangerDetails(groupName, activityName) {
    return request({
        url: '/activity/managerDetail',
        method: 'post',
        params: { groupName, activityName }
    });
}

export function modDesc(groupName, activityName, description, attachment, image) {
    return request({
        url: '/activity/modifyDescription',
        method: 'post',
        params: { groupName, activityName, description, attachment, image }
    });
}

// TODO: modifyInfo, addActivity, deleteActivity with param(activity)
export function modifyInfo(activity) {
    return request({
        url: '/activity/modifyInfo',
        method: 'post',
        data: activity
    });
}

export function addActivity(activity) {
    return request({
        url: '/activity/addActivity',
        method: 'post',
        data: activity
    });
}

export function acceptActivity(activityId) {
    return request({
        url: '/activity/accept',
        method: 'post',
        params: { activityId }
    });
}

export function rejectActivity(activityId) {
    return request({
        url: '/activity/reject',
        method: 'post',
        params: { activityId }
    });
}

// super admin
export function getAllApps(searchInfo) {
    return request({
        url: '/activity/allApps',
        method: 'post',
        params: { searchInfo }
    });
}

export function getAppDetail(groupName, activityName) {
    return request({
        url: '/activity/appDetail',
        method: 'post',
        params: { groupName, activityName }
    });
}