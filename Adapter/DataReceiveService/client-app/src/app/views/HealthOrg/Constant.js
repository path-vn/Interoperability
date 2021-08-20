const listOrgType = [
    { value: 1, name: 'Đơn vị XN sàng lọc' },
    { value: 2, name: 'Đơn vị XN khẳng định' },
    { value: 3, name: 'Đơn vị quản lý' },
    { value: 4, name: 'Đơn vị điều trị' },
    { value: 5, name: 'Đơn vị rà soát' }
]

const listLevel = [
    { value: 1, name: 'Cấp trung ương' },
    { value: 2, name: 'Cấp Vùng' },
    { value: 3, name: 'Cấp tỉnh' },
    { value: 4, name: 'Cấp quận/huyện' },
    { value: 5, name: 'Cấp xã/phường' },
]

module.exports = Object.freeze({
    listOrgType: listOrgType,
    listLevel: listLevel,
});