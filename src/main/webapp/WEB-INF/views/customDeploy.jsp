<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: nipuni
  Date: 8/10/13
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Dhara Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="http://fonts.googleapis.com/css?family=Oxygen|Marck+Script" rel="stylesheet" type="text/css">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/font-awesome.css" rel="stylesheet">
    <link href="assets/css/admin.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <style type="text/css">

        tr:hover {
            background: #F8DBB0;
        }


        th {
            background-color:#F0AF37 ;
            font-size: 100%;
            color: #804000;

        }
        .diplay_table {
            display: table;
            margin: auto;
            text-align: center;
        }

        .table_column th{
            display: table-column;
            color: #804000;
            /*font-family: sans-serif;*/
            height: 10px;
            padding: 15;
        }

        .diplay_table table td {
            border-bottom-color: #000000;
            border-bottom: 1pt solid #000000;
            /*font-family: sans-serif;*/
            font-size: 77%;
            color: #000000;
            padding: 15;
            text-align: center;
        }

        .diplay_table table th {
            /*font-family: "DejaVu Sans Mono";*/
            padding: 15;
        }


    </style>
</head>
<body>


<div class="container">

<div class="row">

<div class="span2">

    <div class="main-left-col">

        <h2><img src="assets/img/DHARAlogo.png"/> </h2>

        <ul class="side-nav">

            <li class="active">
                <a href="index.html"><i class="icon-home"></i> Dashboard</a>
            </li>
            <li class="">
                <a class="" data-toggle="collapse" data-target="#website-dropdown" href="client/client.html"><i class="icon-sitemap"></i> OGC Clients <b class="caret"></b></a>
                <!--<ul id="website-dropdown" class="collapse">-->
                <!--<li><a href="listing.html">Pages</a></li>-->
                <!--<li><a href="listing.html">Menus</a></li>-->
                <!--</ul>-->
            </li>
            <li class="active">
                <a class="" data-toggle="collapse" data-target="#website-dropdown" href="visualize/view.html"><i class="icon-sitemap"></i>Data visualizers<b class="caret"></b></a>
                <!--<ul id="website-dropdown" class="collapse">-->
                <!--<li><a href="listing.html">Pages</a></li>-->
                <!--<li><a href="listing.html">Menus</a></li>-->
                <!--</ul>-->
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#website-dropdown" href="#"><i class="icon-sitemap"></i> Airavata Client <b class="caret"></b></a>
                <ul id="website-dropdown" class="collapse">
                    <li><a href="workflowList.htm">Workflows</a></li>
                    <li><a href="experimentList.htm">Experiments</a></li>
                </ul>
            </li>
            <!--<li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#store-dropdown" href="#"><i class="icon-shopping-cart"></i> Store <b class="caret"></b></a>
                <ul id="store-dropdown" class="collapse">
                    <li><a href="listing.html">Catalogue</a></li>
                    <li><a href="orders.html">Orders</a></li>
                    <li><a href="listing.html">Enquiries</a></li>
                </ul>
            </li>-->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#reports-dropdown" href="#"><i class="icon-signal"></i> Reports <b class="caret"></b></a>
                <ul id="reports-dropdown" class="collapse">
                    <li><a href="generate.html">Generate</a></li>
                    <li><a href="generated.html">Generated</a></li>

                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#members-dropdown" href="#"><i class="icon-group"></i> Members <b class="caret"></b></a>
                <ul id="members-dropdown" class="collapse">
                    <li><a href="members.html">Members</a></li>

                </ul>
            </li>
            <!--<li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#settings-dropdown" href="#"><i class="icon-cogs"></i> Settings <b class="caret"></b></a>
                <ul id="settings-dropdown" class="collapse">
                    <li><a href="listing.html">Payment Processors</a></li>
                    <li><a href="listing.html">Order Statuses</a></li>
                    <li><a href="listing.html">Shipping Methods</a></li>
                    <li><a href="listing.html">Emails</a></li>
                </ul>
            </li>-->
            <!--<li><a href="#"><i class="icon-bullhorn"></i> Alerts <span class="badge badge-warning">2</span></a></li>-->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="collapse" data-target="#help-dropdown" href="#"><i class="icon-info-sign"></i> Help <b class="caret"></b></a>
                <ul id="help-dropdown" class="collapse">
                    <li><a href="faq.html">FAQ</a></li>
                    <li class="active"><a href="userguide.html">User Guide</a></li>
                    <li><a href="support.html">Support</a></li>
                </ul>
            </li>
        </ul>

    </div> <!-- end main-left-col -->

</div> <!-- end span2 -->

<div class="span10">

    <div class="secondary-masthead">

        <ul class="nav nav-pills pull-right">
            <li>
                <a href="http://projectdhara.net78.net/"><i class="icon-globe"></i> View Website</a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-user"></i> Account <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="profile.html">Your Profile</a></li>
                    <li class="active"><a href="form.html">Account Settings</a></li>
                    <li class="divider"></li>
                    <li><a href="">Logout</a></li>
                </ul>
            </li>
        </ul>

        <ul class="breadcrumb">
            <li>
                <a href="#">Portal</a> <span class="divider">/</span>
            </li>
            <li class="active">Airavata Clients</li>
        </ul>

    </div>



    <div class="main-area dashboard">
        <div class="row">
                <div class="page-header">
                    <h2><i class="icon-globe pull-right"></i>Custom Deployment-${workflowId}</h2>
                </div>
        </div>

        <div class="row" style="margin-top: -35px">
            <div class="span10">
                <div class="slate clearfix">
                    <form method="post">

                        <h3>Custom Properties</h3>

                        <table style="text-align:center; vertical-align:middle;">
                            <tr>
                                <td style="width: 140px">Extending Class</td>
                                <td style="width: 140px">
                                    <select name="extendingAlgo" id="extendingAlgo">
                                        <option value="AbstractSelfDescribingAlgorithm">AbstractSelfDescribingAlgorithm</option>
                                        <option value="ConvexHullAlgorithm">ConvexHullAlgorithm</option>
                                        <option value="CoordinateTransformAlgorithm">CoordinateTransformAlgorithm</option>
                                        <option value="DifferenceAlgorithm">DifferenceAlgorithm</option>
                                        <option value="IntersectionAlgorithm">DouglasPeuckerAlgorithm</option>
                                        <option value="TopologyPreservingSimplificationAlgorithm">TopologyPreservingSimplificationAlgorithm</option>
                                        <option value="ContainsAlgorithm">ContainsAlgorithm</option>
                                        <option value="CrossesAlgorithm">CrossesAlgorithm</option>
                                        <option value="DisjointAlgorithm">DisjointAlgorithm</option>
                                        <option value="DistanceAlgorithm">DistanceAlgorithm</option>
                                        <option value="EqualsAlgorithm">EqualsAlgorithm</option>
                                        <option value="IntersectsAlgorithm">IntersectsAlgorithm</option>
                                        <option value="OverlapsAlgorithm">OverlapsAlgorithm</option>
                                        <option value="WithinAlgorithm">WithinAlgorithm</option>
                                        <option value="TouchesAlgorithm">TouchesAlgorithm</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <h3>Workflow Inputs</h3>
                        <table id="customDeploymentInput" style="text-align:center; vertical-align:middle;">
                            <thead>
                            <tr>
                                <th style="width: 140px">
                                    Input Name
                                </th>
                                <th style="width: 140px">
                                    Existing Mapping
                                </th>
                                <th style="width: 140px">
                                    New Mapping Type
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${inputNodes}" var="input" varStatus="loop">
                                <tr>
                                    <td>
                                        <c:out value="${input.nodeName}"></c:out>
                                    </td>
                                    <td>
                                        <c:out value="${input.existingMapping}"></c:out>
                                    </td>
                                    <td>
                                        <select style="width: 80px" name="<c:out value="${input.nodeName}"></c:out>" id="<c:out value="${input.nodeName}"></c:out>">
                                            <option value="Integer">Integer</option>
                                            <option value="Boolean">Boolean</option>
                                            <option value="Short">Short</option>
                                            <option value="Double">Double</option>
                                            <option value="Float">Float</option>
                                            <option value="String">String</option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br/>
                        <h3>Workflow Outputs</h3>
                        <table id="customDeploymentOutput" style="text-align:center;vertical-align:middle;">
                            <thead>
                            <tr>
                                <th style="width: 140px">
                                    Output Name
                                </th>
                                <th style="width: 140px">
                                    Existing Mapping
                                </th>
                                <th style="width: 140px">
                                    New Mapping Type
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${outputNodes}" var="output" varStatus="loop">
                                <tr>
                                    <td>
                                        <c:out value="${output.nodeName}"></c:out>
                                    </td>
                                    <td>
                                        <c:out value="${output.existingMapping}"></c:out>
                                    </td>
                                    <td>
                                        <select style="width: 80px" name="<c:out value="${output.nodeName}"></c:out>" id="<c:out value="${output.nodeName}"></c:out>">
                                            <option value="Integer">Integer</option>
                                            <option value="Boolean">Boolean</option>
                                            <option value="Short">Short</option>
                                            <option value="Double">Double</option>
                                            <option value="Float">Float</option>
                                            <option value="String">String</option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <table style="text-align:right">
                            <tbody>
                            <tr>
                                <td style="width:440px; text-align:right">
                                    <input type="submit"></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="span10 footer">

                <p>&copy; 2013 Project Dhara</p>

            </div>

        </div>


    </div> <!-- end span10 -->

</div> <!-- end row -->

</div> <!-- end container -->


<%--<h3>Experiment List</h3>--%>

<%--<table>--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<td>Experiment Name</td>--%>
<%--<td>Last updated</td>--%>
<%--<td>Author</td>--%>
<%--</tr>--%>
<%--</thead>--%>

<%--<c:forEach items="${message}" var="experiment" varStatus="loop">--%>
<%--<tr>--%>
<%--<td>--%>
<%--<c:out value="${experiment.name}"></c:out>--%>
<%--</td>--%>
<%--<td>--%>
<%--<c:out value="${experiment.updatedTime}"></c:out>--%>
<%--</td>--%>
<%--<td>--%>
<%--<c:out value="${experiment.author}"></c:out>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>


<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="assets/js/bootstrap.js"></script>


</body>
</html>