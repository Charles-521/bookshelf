<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="./common/header.jsp"%>
<!DOCTYPE html>

<html lang="en">
<head>
 	<link href="css/kendo.common.min.css" rel="stylesheet">
    <link href="css/kendo.custom.css" rel="stylesheet">

            <style>
                .k-detail-cell .k-tabstrip .k-content {
                    padding: 0.2em;
                }
                .employee-details ul
                {
                    list-style:none;
                    font-style:italic;
                    margin: 15px;
                    padding: 0;
                }
                .employee-details ul li
                {
                    margin: 0;
                    line-height: 1.7em;
                }

                .employee-details label
                {
                    display:inline-block;
                    width:90px;
                    padding-right: 10px;
                    text-align: right;
                    font-style:normal;
                    font-weight:bold;
                }
            </style>
</head>
<body>


	<!--================grid Area =================-->
	<section  class="cart_area ">
		<div id="cartsection" class="container">
			<div id="example">
            	<div id="grid"></div>
        	</div>
        	</div>
		</section>
	<!--================End grid Area =================-->


	
	<script src="http://kendo.cdn.telerik.com/2018.3.1017/js/jquery.min.js"></script>
  	<script src="http://kendo.cdn.telerik.com/2018.3.1017/js/kendo.all.min.js"></script>


<script>
            $(document).ready(function() {

            var ds = new kendo.data.DataSource({
                        allowUnsort: true,
                        transport: {
                            read: {
                                url: "GetOrdersServlet",
                                cache: false,
                                type: "POST",
                                dataType: "json"
                            },
                            parameterMap: function (options, operation) {
                                if (operation === "read") {
                                    /* if (typeof options.isEmpty === 'undefined') {
                                        $("#empty").hide();
                                        $("#grid").show();
                                    }
                                    else {
                                    	 $("#empty").show();
                                         $("#grid").hide();

                                    } */
                                    console.log(options);
                                    return {
                                        request: options
                                    };
                                }
                            }
                        },
                        batch: true,
                        schema: {
                            /* data: function (response) {
                                console.log(response.Data);
                                return response.Data;
                            },
                            total: function (response) {
                                return response.DataCount;
                            }, */
                            model: {
                                id: "id",
                                fields: {
                                    orderNo: {
                                        type: "string"
                                    },
                                    createDate: {
                                        type: "date",

                                    },
                                    payment: {
                                        type: "string",

                                    }
                                }
                            }
                        },
                        pageSize: 20
                    });


                    // var dsOd = new kendo.data.DataSource({
                    //     serverPaging: true,
                    //     serverSorting: true,
                    //     allowUnsort: true,
                    //     serverFiltering: true,
                    //     //filter: { field: "orderID", operator: "eq", value: data.OrderID },
                    //     transport: {
                    //         read: {
                    //             url: "GetOrderDetailsServlet",
                    //             cache: false,
                    //             type: "post",
                    //             dataType: "json"
                    //         },
                    //        /*  parameterMap: function (options, operation) {
                    //             if (operation === "read") {
                    //                 if (options.isEmpty) {
                    //                     alert('empty');
                    //                 }
                    //                 return {
                    //                     request: options
                    //                 };
                    //             }
                    //         } */
                    //     },
                    //     batch: true,
                    //     schema: {
                    //         data: function (response) {
                    //             return response.Data;
                    //         },
                    //         total: function (response) {
                    //             return response.DataCount;
                    //         },
                    //         model: {
                    //             id: "orderDetailID",
                    //             fields: {
                    //             	orderID: {
                    //                     type: "string"
                    //                 },
                    //             	name: {
                    //                     type: "string"
                    //                 },
                    //                 contact: {
                    //                     type: "date",
                    //                     editable: false
                    //                 },
                    //                 price: {
                    //                     type: "string",
                    //                     editable: false
                    //                 }
                    //             }
                    //         }
                    //     },
                    //     pageSize: 20
                    // });

/*                     var element = $("#grid").kendoGrid({
                        dataSource: ds,
                        height: 550,
                        pageable: false,
                        detailTemplate: kendo.template($("#template").html()),
                        detailInit: detailInit,
                        dataBound: function() {
                            this.expandRow(this.tbody.find("tr.k-master-row").first());
                        },
                        columns: [
                            {
                                field: "orderNo",
                                title: "Order No.",
                                width: "120px"
                            },
                            {
                                field: "createdDate",
                                title: "Order Date",
                                width: "120px"
                            },
                            {
                                field: "payment",
                                field: "Price (Original)",
                                width: "120px"
                            }
                        ]
                    });
                });

                function detailInit(e) {
                    var detailRow = e.detailRow;
                    detailRow.find(".tabstrip").kendoTabStrip({
                        animation: {
                            open: { effects: "fadeIn" }
                        }
                    });
                    detailRow.find(".orders").kendoGrid({
                        dataSource: dsOd,
                        scrollable: false,
                        sortable: true,
                        pageable: true,
                        columns: [
                            { field: "name", title:"Book Name", width: "110px" },
                            { field: "contact", title:"Contact No.", width: "110px" },
                            { field: "price", title:"Price (Original)" },
                        ]
                    });
                } */
                var wnd,
                detailsTemplate;


                var grid = $("#grid").kendoGrid({
                    dataSource: ds,
                    /* pageable: true,
                    height: 550, */
                    columns: [
                        {
                            field: "orderNo",
                            title: "Order No.",
                            width: "200px"
                        },
                        {
                            field: "createDate",
                            title: "Order Date",
                            width: "120px"
                        },
                        {
                            field: "payment",
                            field: "Price (Original)",
                            width: "120px"
                        },
                        //{ command: { text: "View Details", click: showDetails }, title: " ", width: "180px" }
                    ]

                }).data("kendoGrid");

                /* wnd = $("#details")
                    .kendoWindow({
                        title: "Customer Details",
                        modal: true,
                        visible: false,
                        resizable: false,
                        width: 300
                    }).data("kendoWindow");

                detailsTemplate = kendo.template($("#template").html()); */


            function showDetails(e) {
                e.preventDefault();

                var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                wnd.content(detailsTemplate(dataItem));
                wnd.center().open();
            };
           });
           </script>
</body>

</html>
