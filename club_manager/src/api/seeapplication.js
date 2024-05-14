import request from '@/utils/request'

export function getSeeApplications(groupname) {
    return request({
        url: '/application/recvapps',
        method: 'post',
        params: { groupname }
    })
}

export function getAppDetails(id) {
    return request({
        url: '/application',
        method: 'post',
        params: { id }
    });
}

export function acceptApplication(applicationid) {
    return request({
        url: '/application/accept',
        method: 'post',
        params: { applicationid }
    })
}

export function rejectApplication(applicationid) {
    return request({
        url: '/application/reject',
        method: 'post',
        params: { applicationid }
    })
}