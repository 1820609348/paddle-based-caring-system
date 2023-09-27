//模拟后端对数据进行应答
//要在main.js中import，不然无法正常工作
import Mock from "mockjs"
import {baseURL} from "@/api/mockServer/index";

const tableData = [
    {
        name: '星野爱',
        age: '78',
        status: '摔倒',
        pos: '猛追湾街道',
    },
    {
        name: '古河渚',
        age: '76',
        status: '摔倒',
        pos: '沙河校区',
    },
    {
        name: '空门苍',
        age: '66',
        status: '走失',
        pos: '建设北路',
    },
    {
        name: '久岛鸥',
        age: '82',
        status: '走失',
        pos: '玉林北路',
    },
    {
        name: '鸣濑白羽',
        age: '80',
        status: '摔倒',
        pos: '玉林南路',
    },
];
const linGraph = [
    {
        name: 'Day Fail',
        type: 'line',
        data: [0, 0, 0, 0, 0, 0, 0]
    },
    {
        name: 'Day Success',
        type: 'line',
        data: [20, 15, 12, 18, 9, 13, 13]
    },
    {
        name: 'Day Total',
        type: 'line',
        data: [20, 15, 12, 18, 9, 13, 13]
    },
    {
        name: 'History Fail',
        type: 'line',
        data: [0, 0, 0, 0, 0, 0, 0],
        yAxisIndex: 1
    },
    {
        name: 'History Success',
        type: 'line',
        data: [26, 41, 53, 71, 80, 93, 106],
        yAxisIndex: 1
    },
    {
        name: 'History Total',
        type: 'line',
        data: [44, 59, 71, 89, 98, 111, 124],
        yAxisIndex: 1
    }
]
const barGraph = [
    {
        name: '走失',
        data: [7, 8, 10, 4, 7, 7, 5],
        type: 'bar',
    },
    {
        name: '摔倒',
        data: [13, 7, 2, 14, 2, 6, 8],
        type: 'bar',
    }
];
const pieGraph = [
    {value: 60, name: '锦江区'},
    {value: 73, name: '青羊区'},
    {value: 64, name: '成华区'},
    {value: 102, name: '金牛区'},
    {value: 51, name: '武侯区'}

];
Mock.mock(baseURL + '/home/getTableData', function () {
    return tableData
})
Mock.mock(baseURL + '/home/getLinData', function () {
    return linGraph
})
Mock.mock(baseURL + '/home/getBarData', function () {
    return barGraph
})
Mock.mock(baseURL + '/home/getPieData', function () {
    return pieGraph
})