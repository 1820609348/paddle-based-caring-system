import Mock from "mockjs"
import {baseURL} from "@/api/mockServer/index";
import {addUserInfo} from "@/api";

var tableData = [
    {
        id: 1,
        age: 78,
        option: '跌倒',
        name: "堀京子",
        team: '暖阳救援队一队',
        time: '2023-09-07',
        pos: '成华区沙河校区',
        rank: '一级',
        status: '等待救援',
        lng: 104.10193,
        lat: 30.65993
    },
    {
        id: 2,
        age: 76,
        option: '跌倒',
        name: "宫村伊澄",
        team: '暖阳救援队二队',
        time: '2023-09-07',
        pos: '成华区建设巷',
        rank: '二级',
        status: '等待救援',
        lng: 104.05114,
        lat: 30.69126
    },
    {
        id: 3,
        age: 82,
        option: '疾病',
        name: "吉川由纪",
        team: '暖阳救援队三队',
        time: '2023-09-07',
        pos: '锦江区太古里',
        rank: '三级',
        status: '等待救援',
        lng: 104.08347,
        lat: 30.65614
    },
]
Mock.mock(baseURL + '/recv/getRecvInfo', function () {
    return tableData
})
Mock.mock(baseURL + '/recv/recvMission', function (data) {
    let row = JSON.parse(data.body)
    tableData.forEach((item, index) => {
        if (item.id === row.id) {
            tableData.splice(index, 1);
        }
    })
    addUserInfo(row)
    return true
})