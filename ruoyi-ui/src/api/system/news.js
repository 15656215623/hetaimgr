import request from '@/utils/request'
// 查询news所有的列表
export function listNews(query) {
  return request({
    url: '/system/news/list',
    method: 'get',
    params: query
  })
}


// 查询news最新的7条数据
export function listCurrentNews(query) {
  return request({
    url: '/system/news/getCurrentList',
    method: 'get',
    params: query
  })
}
//查询该条信息的上一条与下一条信息
export function getPreAndNextNews(id) {
  return request({
    url: '/system/news/getPreAndNextNews/' + id,
    method: 'get'
  })
}

// 查询news详细
export function getNews(id) {
  return request({
    url: '/system/news/' + id,
    method: 'get'
  })
}

// 新增news
export function addNews(data) {
  return request({
    url: '/system/news',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data
  })
}

// 修改news
export function updateNews(data) {
  return request({
    url: '/system/news/edit',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data
  })
}

// 删除news
export function delNews(id) {
  return request({
    url: '/system/news/' + id,
    method: 'delete'
  })
}
