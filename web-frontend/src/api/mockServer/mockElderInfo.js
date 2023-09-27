import Mock from "mockjs"
import {baseURL} from "@/api/mockServer/index";

let idCount = 8;
const tableData = [
    {
        id: 1,
        name: "星野瑠美衣",
        gender: "女性",
        age: 78,
        status: "健康",
        pos: '成华区沙河校区',
        family: "配偶为星野爱久爱海，女儿星野爱"
    },
    {
        id: 2,
        name: "星野爱久爱海",
        gender: "男性",
        age: 76,
        status: "健康",
        pos: '成华区沙河校区',
        family: "配偶为星野爱，女儿星野爱",
    },
    {
        id: 3,
        name: "有马加奈",
        gender: '女性',
        age: 82,
        status: "良好",
        pos: '锦江区太古里',
        family: "无配偶，无子女"
    },
    {
        id: 4,
        name: "黑川茜",
        gender: "女性",
        age: 69,
        status: "一般",
        pos: "锦江区天府广场",
        family: "配偶为夏目，无子女"
    },
    {
        id: 5,
        name: '鹫见夕雪',
        gender: "女性",
        age: 71,
        pos: "锦江区天府广场",
        status: "良好",
        family: "无配偶，有儿子夏颖"
    },
    {
        id: 6,
        name: '古河渚',
        gender: "男性",
        age: 64,
        pos: "锦江区天府广场",
        status: "健康",
        family: "配偶为冈崎朋也，有女儿冈崎汐"
    },
    {
        id: 7,
        name: '冈崎汐',
        gender: "女性",
        age: 86,
        pos: "成华区九里堤校区",
        status: "良好",
        family: "无信息"
    },
    {
        id: 8,
        name: '坂上智代',
        gender: "女性",
        age: 93,
        pos: "成华区建设北路",
        status: "健康",
        family: "无信息"
    },
]
//查询
Mock.mock(baseURL + '/elder/getUserInfo', function () {
    return tableData
})
//增加
Mock.mock(baseURL + '/elder/addUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    idCount++;
    newValue["id"] = idCount
    tableData.unshift(newValue)
    return true;
})
//修改
Mock.mock(baseURL + '/elder/updateUserInfo', function (data) {
    let newValue = JSON.parse(data.body)
    tableData.forEach((item, index) => {
        if (item.id === newValue.id) {
            tableData[index] = newValue
        }
    })
    return true
})
//删除
Mock.mock(baseURL + '/elder/deleteUserInfo', function (data) {
    let id = JSON.parse(data.body).id;
    tableData.forEach((item, index) => {
        if (item.id === id) {
            tableData.splice(index, 1);
        }
    })
    return true
})