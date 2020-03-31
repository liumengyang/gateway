# gateway
init.sql 为数据库初始化脚本

json数据结构：
{
    "predicateDefinition":[
        {
            "predicateValue":"/liumengyang/**",
            "name":"Path",
            "predicateKey":"pattern"
        }
    ],
    "id":"test_001",
    "uri":"https://www.jd.com/",
    "filterDefinition":[
        {
            "filterKey":"parts",
            "filterValue":"1",
            "name":"StripPrefix"
        }
    ],
    "order":"0"
}

predicateDefinition为数组结构，数组项为gateway断言
filterDefinition 为数组结构，数组项为filter,可在其中进行鉴权等处理
