import Mock from "mockjs"
import {baseURL} from "@/api/mockServer/index";

let idCount = 8;
const tableData = [
    {
        name: "张晓明",
        guardian: "星野瑠美衣",
        relationship: "孙子",
        registrationDate: "2023-03-15",
        familySituation: "三代同堂，幸福和睦",
        residence: "北京市朝阳区东三环"
    },
    {
        name: "李小红",
        guardian: "星野瑠美衣",
        relationship: "外孙女",
        registrationDate: "2023-02-28",
        familySituation: "星野瑠美衣独居，李小红每日照顾",
        residence: "上海市浦东新区"
    },
    {
        name: "王建国",
        guardian: "有马加奈",
        relationship: "外甥",
        registrationDate: "2023-04-10",
        familySituation: "王建国的家庭与有马加奈同住，共同生活",
        residence: "广州市天河区"
    },
    {
        name: "陈秀芳",
        guardian: "黑川茜",
        relationship: "孙女",
        registrationDate: "2023-01-20",
        familySituation: "陈秀芳的家庭与黑川茜同住，关系亲近",
        residence: "深圳市福田区"
    },
    {
        name: "刘亮",
        guardian: "有马加奈",
        relationship: "邻居",
        registrationDate: "2023-05-05",
        familySituation: "刘亮照顾她的生活需求",
        residence: "成都市武侯区"
    },
    {
        name: "杨梅",
        guardian: "鹫见夕雪",
        relationship: "朋友",
        registrationDate: "2023-03-22",
        familySituation: "鹫见夕雪独居，杨梅每周来陪伴她",
        residence: "南京市玄武区"
    },
    {
        name: "刘强",
        guardian: "冈崎汐",
        relationship: "儿子",
        registrationDate: "2023-02-10",
        familySituation: "刘强的家庭与冈崎汐同住，共同照顾",
        residence: "重庆市渝中区"
    },
    {
        name: "王莉",
        guardian: "古河渚",
        relationship: "朋友",
        registrationDate: "2023-04-28",
        familySituation: "古河渚丧偶，王莉每周来陪伴她",
        residence: "杭州市西湖区"
    },
    {
        name: "李明",
        guardian: "星野爱久爱海",
        relationship: "外孙",
        registrationDate: "2023-01-15",
        familySituation: "李明的家庭与星野爱久爱海同住，共同生活",
        residence: "武汉市江汉区"
    },
    {
        name: "周燕",
        guardian: "星野爱久爱海",
        relationship: "朋友",
        registrationDate: "2023-05-15",
        familySituation: "星野爱久爱海丧偶，周燕每周来陪伴她",
        residence: "成都市青羊区"
    }
];
//查询
Mock.mock(baseURL + '/family/getUserInfo', function () {
    return tableData
})
//增加
Mock.mock(baseURL + '/family/addUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    idCount++;
    newValue["id"] = idCount
    tableData.unshift(newValue)
    return true;
})
//修改
Mock.mock(baseURL + '/family/updateUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    tableData.forEach((item, index) => {
        if (item.id === newValue.id) {
            tableData[index] = newValue
        }
    })
    return true
})
//删除
Mock.mock(baseURL + '/family/deleteUserInfo', function (data) {
    let id = JSON.parse(data.body).id;
    tableData.forEach((item, index) => {
        if (item.id === id) {
            tableData.splice(index, 1);
        }
    })
    return true
})