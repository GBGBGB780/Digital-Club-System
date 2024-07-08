import request from '@/utils/request'

// 本社未审核的活动
export function getMyApps(searchInfo, groupName) {
    let data = new URLSearchParams();
    data.append("searchInfo", searchInfo)
    data.append("groupName", groupName)
    return request({
        url: '/activity/myApps',
        method: 'post',
        data: data
    });
}

// 本社审核通过和不通过的活动
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
    let data = new URLSearchParams();
    data.append("name", activity.name)
    data.append("organizer", activity.organizer,)
    data.append("type", activity.type,)
    data.append("place", activity.place,)
    data.append("time", activity.time,)
    data.append("arrange", activity.arrange,)
    data.append("desc", activity.desc,)
    data.append("groupName", activity.groupName)
    return request({
        url: '/activity/modifyInfo',
        method: 'post',
        data: data
    });
}

export function addActivity(activity) {
    return request({
        url: '/activity/addActivity',
        method: 'post',
        activity: activity
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