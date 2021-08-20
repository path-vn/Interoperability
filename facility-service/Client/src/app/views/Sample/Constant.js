const listSampleType = [
    { id: 1, name: 'Mẫu thường' },
    { id: 2, name: 'Mẫu gộp' },
]

const listSampleStatus = [
    { id: 'Draft', name: "Bản nháp" },
    { id: 'Pending', name: "Chờ xử lý" },
    { id: 'Accepted', name : "Đã được chấp nhận"},
    { id: 'Rejected', name: "Mẫu không thể sử dụng" },
    { id: 'Canceled', name : "Mẫu bị hủy"},
]

const listSampleResult = [
    { id: 'Checking', name: "Dương tính chờ xác nhận" },
    { id: 'Positive', name: "Dương tính" },
    { id: 'Negative', name : "Âm tính"}
]

const listGender = [
    { id: 'M', name: 'Nam' },
    { id: 'F', name: 'Nữ' },
    { id: 'U', name: 'Không rõ' }
]

const listOrgType = [
    { id: 0 , name: 'Tất cả'},
    { id: 1 , name: 'Đơn vị xét nghiệm'},
    { id: 2, name: 'Đơn vị lấy mẫu'},
    { id: 3, name: 'Đơn vị cách ly'}
]

const listCollectOrgType = [
    // { id: 0 , name: 'Tất cả'},
    { id: 2, name: 'Đơn vị lấy mẫu'}
]

const listLabTestOrgType = [
    // { id: 0 , name: 'Tất cả'},
    { id: 1 , name: 'Đơn vị xét nghiệm'}
]

const statusSymptom = [
    { id : 1 , name: 'Có'},
    { id : 2 , name: 'Không'},
]
let listSampleStatusCollectionOrg = [
    { id: 'Draft', name: "Bản nháp" },
    { id: 'Pending', name: "Chờ xử lý" }
]
let listSampleStatusLabtest = [
    { id: 'Pending', name: "Chờ xử lý" },
    { id: 'Accepted', name: "Đã được chấp nhận" },
    { id: 'Rejected', name: "Mẫu không thể sử dụng" },
    { id: 'Canceled', name: "Mẫu bị hủy" },
]

let SampleTestTypeEnum = [
    { id: 0 , name: "Nôi bộ"},
    { id: 1 , name: "Mẫu nhận từ ngoài vào"},
    { id: 2 , name: "Mẫu gửi ra ngoài"}
]

let ListIsExternalOrg = [
    {id: "null" , name: "Tất cả"},
    {id: true , name: "Là đơn vị ngoại tỉnh" },
    {id: false, name: "Là đơn vị nội tỉnh"}
]
module.exports = Object.freeze({
    listSampleType: listSampleType,
    listSampleStatus: listSampleStatus,
    listGender: listGender,
    listOrgType: listOrgType,
    statusSymptom: statusSymptom,
    listSampleResult: listSampleResult,
    listCollectOrgType: listCollectOrgType,
    listLabTestOrgType: listLabTestOrgType,
    listSampleStatusCollectionOrg: listSampleStatusCollectionOrg,
    listSampleStatusLabtest: listSampleStatusLabtest,
    SampleTestTypeEnum: SampleTestTypeEnum,
    ListIsExternalOrg: ListIsExternalOrg
  });
