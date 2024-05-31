import request from '@/utils/request'

export function getSeeApplications(groupName) {
    return request({
        url: '/application/recvapps',
        method: 'post',
        params: { groupName }
    })
}

export function getAppDetails(id) {
    return request({
        url: '/application/recvapp',
        method: 'post',
        params: { id }
    });
}

export function acceptApplication(applicationId) {
    return request({
        url: '/application/accept',
        method: 'post',
        params: { applicationId }
    })
}

export function rejectApplication(applicationId) {
    return request({
        url: '/application/reject',
        method: 'post',
        params: { applicationId }
    })
}