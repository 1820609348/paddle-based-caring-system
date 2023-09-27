import Mock from "mockjs"
import {baseURL} from "@/api/mockServer/index";

let idCount = 8;
const tableData = [
    {
        id: 1,
        age: 78,
        option: '跌倒',
        name: "星野瑠美衣",
        team: '蓝天救援队一队',
        pos: '成华区沙河校区',
        rank: '二级',
        status: '救援中',
    },
    {
        id: 2,
        age: 76,
        option: '跌倒',
        name: "星野爱久爱海",
        team: '阳光救援队一队',
        pos: '成华区建设巷',
        rank: '三级',
        status: '救援中',
    },
    {
        id: 3,
        age: 82,
        option: '疾病',
        name: "有马加奈",
        team: '阳光救援队三队',
        pos: '锦江区太古里',
        rank: '一级',
        status: '救援中',
    },
    {
        id: 4,
        age: 69,
        option: '疾病',
        name: "黑川茜",
        team: '蓝天救援队三队',
        pos: "锦江区天府广场",
        rank: '二级',
        status: '救援中',
    },
    {
        id: 5,
        age: 71,
        option: '摔倒',
        name: '鹫见夕雪',
        team: '暖阳救援队三队',
        pos: "锦江区天府广场",
        rank: '三级',
        status: '救援中',
    },
    {
        id: 6,
        age: 64,
        option: '疾病',
        name: '古河渚',
        team: '暖阳救援队一队',
        pos: "锦江区天府广场",
        rank: '三级',
        status: '救援成功',
    },
    {
        id: 7,
        age: 86,
        option: '疾病',
        name: '冈崎汐',
        team: '蓝天救援队一队',
        pos: "成华区九里堤校区",
        rank: '二级',
        status: '救援成功',
    },
    {
        id: 8,
        age: 93,
        option: '摔倒',
        name: '智代',
        team: '阳光救援队三队',
        pos: "成华区建设北路",
        rank: '一级',
        status: '救援成功',
    },
]
//查询
Mock.mock(baseURL + '/user/getUserInfo', function () {
    return tableData
})
//增加
Mock.mock(baseURL + '/user/addUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    idCount++;
    newValue["id"] = idCount
    tableData.unshift(newValue)
    return true;
})
//修改
Mock.mock(baseURL + '/user/updateUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    tableData.forEach((item, index) => {
        if (item.id === newValue.id) {
            tableData[index] = newValue
        }
    })
    return true
})
//删除
Mock.mock(baseURL + '/user/deleteUserInfo', function (data) {
    let id = JSON.parse(data.body).id;
    tableData.forEach((item, index) => {
        if (item.id === id) {
            tableData.splice(index, 1);
        }
    })
    return true
})