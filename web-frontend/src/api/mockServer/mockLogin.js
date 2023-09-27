import Mock from "mockjs";
import {baseURL} from "@/api/mockServer/index";


var userInfo = [
    {
        id: 1,
        username: "江畔晚风拂柳",
        email: "1820609348@qq.com",
        secret: "123456",

    }
]
//登录检查
Mock.mock(baseURL + '/login/login', function (data) {
    let newValue = JSON.parse(data.body)
    let username = newValue.username
    let secret = newValue.secret
    let res = {}
    let flag = true
    userInfo.forEach((val) => {
        if (val.username === username && val.secret === secret) {
            flag = false
            res = {
                status: true,
            }
        }
    })
    if (flag) {
        res = {
            status: false
        }
    }
    return res
})
//注册检查
Mock.mock(baseURL + '/login/reg', function (data) {
    let newValue = JSON.parse(data.body)
    let username = newValue.username
    let res = {}
    let flag = true
    userInfo.forEach((val) => {
        if (val.username === username) {
            flag = false
            res = {
                status: false,
            }
        }
    })
    if (flag) {
        userInfo.push(newValue);
        res = {
            status: true
        }
    }
    return res
})