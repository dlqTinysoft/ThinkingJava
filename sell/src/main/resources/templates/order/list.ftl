<#--要分清属性和方法-->
<#--<h1>${orderDTOPage.getTotalPages()}</h1>-->

<#--遍历数据 ,www.ibootstrap.cn 这个网站很好对于写网站-->
<#--<#list orderDTOPage.content as orderDTO>-->
<#--${orderDTO.orderId}-->
<#--</#list>-->

<html>
<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">
     <#--边栏-->
     <#include "../common/nav.ftl">
     <#--主要内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <#--表格的头thead-->
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <#--表格的身体tbody-->
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                        <tr class="success">
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum().message}</td>
                            <td>${orderDTO.getPayStatusEnum().message}</td>
                            <td>${orderDTO.createTime}</td>
                            <td><a href="/sell/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td>
                                <#--这个这个非常好，使用枚举来解决程序不用写死-->
                                <#if orderDTO.getOrderStatusEnum().message="新订单">
                                    <a href="/sell/order/cancle?orderId=${orderDTO.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>

                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#--使用#if标签来控制上一页的显示功能-->
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else >
                        <li><a href="/sell/order/list?page=${currentPage-1}&size=#{size}">上一页</a></li>
                    </#if>

                    <#--#list标签来进行显示所有的页码-->
                    <#list 1..orderDTOPage.getTotalPages() as index>

                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else >
                            <li><a href="/sell/order/list?page=${index}&size=${size}">${index}</a></li>

                        </#if>
                    </#list>
                        <#--下一页的显示功能实现-->
                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else >
                        <li><a href="/sell/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                    </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>