逼斗士Java后台
======
#启动演示
1. 执行bin下refresh-db.sh，初始化数据
2. eclipse 下运行src/test/java/com/banyou/backend/BackendServer

#路径
1. base 路径 127.0.0.1:8081/bidoushi/
2. 商品
    1. list get 127.0.0.1:8081/bidoushi/product
    2. create get 127.0.0.1:8081/bidoushi/product/create
    3. update get 127.0.0.1:8081/bidoushi/product/update/{id}
    4. submit post 127.0.0.1:8081/bidoushi/product/update/{id}
    5. delete 127.0.0.1:8081/bidoushi/product/delete/{id}
    6. listProductDest get 127.0.0.1:8081/bidoushi/product/{id}/dest
    7. listProductDest get 127.0.0.1:8081/bidoushi/product/{id}/tag
3. dest 
    1. list get 127.0.0.1:8081/bidoushi/dest
    2. list.json get 127.0.0.1:8081/bidoushi/dest/list.json
    3. 
    
4. tag
    1. list.json get 127.0.0.1:8081/bidoushi/tag/list.json

5. upload
    1. uploadfile post 127.0.0.1:8081/bidoushi/upload
    2. downloadfile get 127.0.0.1/8081/bidoushi/download

