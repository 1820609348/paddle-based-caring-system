//这里调用utils中二次封装的axios写在组件中可以使用到的函数，也可以直接使用axios
import axios from "axios"

const baseUrl = "http://127.0.0.1:8080/api"
//请求home组件中所有get方法拿到的数据
export const getData = (param) => {
    return axios({
        url: baseUrl + `/home/${param}`,
        method: "get"
    })
}
//请求user组件中所有axios请求
export const getUserInfo = () => {
    return axios({
        url: baseUrl + "/user/getUserInfo",
        method: "get"
    })
}
export let addUserInfo = function (data) {
    return axios({
        url: baseUrl + '/user/addUserInfo',
        method: "post",
        data: data
    })
}
export let updateUserInfo = function (data) {
    return axios({
        url: baseUrl + '/user/updateUserInfo',
        method: "post",
        data: data
    })
}
export let deleteUserInfo = function (id) {
    return axios({
        url: baseUrl + `/user/deleteUserInfo?id=${id}`,
        method: "get",
    })
}
//login界面所有请求
export let postLogin = function (data, type) {//data是表单，type区分login/reg
    return axios({
        url: baseUrl + `/login/${type}`,
        method: "post",
        data: data
    })
}
//recv界面所有请求
export let getRecvInfo = function () {
    return axios({
        url: baseUrl + `/recv/getRecvInfo`,
        method: "get",
    })
}
export let recvMission = function (data) {
    return axios({
        url: baseUrl + `/recv/recvMission`,
        method: "post",
        data: data
    })
}
//请求elder组件中所有axios请求
export const getElderInfo = () => {
    return axios({
        url: baseUrl + "/elder/getUserInfo",
        method: "get"
    })
}
export let addElderInfo = function (data) {
    return axios({
        url: baseUrl + '/elder/addUserInfo',
        method: "post",
        data: data
    })
}
export let updateElderInfo = function (data) {
    return axios({
        url: baseUrl + '/elder/updateUserInfo',
        method: "post",
        data: data
    })
}
export let deleteElderInfo = function (id) {
    return axios({
        url: baseUrl + `/elder/deleteUserInfo?id=${id}`,
        method: "get",
    })
}
//请求family组件中所有axios请求
export const getFamilyInfo = () => {
    return axios({
        url: baseUrl + "/family/getUserInfo",
        method: "get"
    })
}
export let addFamilyInfo = function (data) {
    return axios({
        url: baseUrl + '/family/addUserInfo',
        method: "post",
        data: data
    })
}
export let updateFamilyInfo = function (data) {
    return axios({
        url: baseUrl + '/family/updateUserInfo',
        method: "post",
        data: data
    })
}
export let deleteFamilyInfo = function (id) {
    return axios({
        url: baseUrl + `/family/deleteUserInfo?id=${id}`,
        method: "get",
    })
}