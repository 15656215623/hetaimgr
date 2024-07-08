import request from '@/utils/request'

// 查询attachments列表
export function listAttachments(query) {
  return request({
    url: '/system/attachments/list',
    method: 'get',
    params: query
  })
}

// 查询attachments详细
export function getAttachments(id) {
  return request({
    url: '/system/attachments/' + id,
    method: 'get'
  })
}

// 新增attachments
export function addAttachments(data) {
  return request({
    url: '/system/attachments',
    method: 'post',
    data: data
  })
}

// 修改attachments
export function updateAttachments(data) {
  return request({
    url: '/system/attachments',
    method: 'put',
    data: data
  })
}

// 删除attachments
export function delAttachments(id) {
  return request({
    url: '/system/attachments/' + id,
    method: 'delete'
  })
}
