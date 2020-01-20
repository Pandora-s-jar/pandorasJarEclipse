<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>profile</title>
    <link rel="stylesheet" href="css/profileStyle.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="scripts/profileScript.js"></script>
    <script src="scripts/changeHeight.js"></script>
</head>

<body id="profile">
    <c:if test="${canSee}">
        <jsp:include page="header.jsp" />
        <div class="row" id="firstRow">
            <div class="col-3" id="divProfileMenu">
                <jsp:include page="profileMenu.html"></jsp:include>
            </div>
            <div class="col" >
                <div class="row text-center">
                    <div class="col-xl-5 text-center">
                        <div id="photoProfile">
                            <c:if test="${empty user.image}">
                                <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
                            </c:if>
                            <c:if test="${not empty user.image}">
                                <img src="/PrintImage?id=${user.id}" height="220" width="220">
                            </c:if>
                        </div>
                        <c:if test="${not friend}">
                            <h6 class="text-center">Upload a different photo...</h6>
                            <form class="text-center d-block" id="profileDetails" method="post" action="UploadImage" enctype="multipart/form-data">
                                <input class="border rounded d-block" type="file" id="inputFile" name="inputFile">
                                <button class="btn btn-primary border rounded btn-color" type="submit" id="uploadBtn">Upload</button>
                            </form>
                        </c:if>
                    </div>
                    <div class="col">
                        <form class="text-center" id="profileDetails" method="post" action="changeProfileDetails?change=1">
                            <div class="jumbotron" id="tableJumbotron">
                                <div class="table-responsive text-center" id="divTable">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td class="text-center field-name">Username:</td>
                                                <td><input class="form-control" type="text" name="inputUsername" id="inputUsername" readonly value="${user.username}"></td>
                                                <c:if test="${not friend}">
                                                    <td style="width: 127px;"><button class="btn btn-primary fas fa-edit btn-color" type="button" id="btnChangeUsername"></button></td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <td class="field-name">Email:</td>
                                                <td><input class="form-control" type="email" name="inputEmail" id="inputEmail" readonly value="${user.email}"></td>
                                                <c:if test="${not friend}">
                                                    <td><button class="btn btn-primary fas fa-edit btn-color" type="button" id="btnChangeEmail"></button></td>
                                                </c:if>
                                            </tr>
                                            <c:if test="${not friend}">
                                            <tr>
                                                <td class="field-name">Password:</td>
                                                <td><input class="form-control" type="text" name="inputPassword" id="inputPassword" readonly value="${user.password}"></td>

                                                    <td><button class="btn btn-primary fas fa-edit btn-color" type="button" id="btnChangePassword"></button></td>

                                            </tr>
                                            </c:if>
                                            <tr>
                                                <td class="field-name">Description:</td>
                                                <td><input class="form-control" type="text" name="inputDescription" id="inputDescription" readonly value="${user.description}"></td>
                                                <c:if test="${not friend}">
                                                    <td><button class="btn btn-primary fas fa-edit btn-color" type="button" id="btnChangeDescription"></button></td>
                                                </c:if>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <c:if test="${not friend}">
                                <button class="btn btn-primary text-center border rounded btn-color" type="sumbit" id="saveBtn">Save</button>
                            </c:if>
                        </form>
                    </div>
                </div>
                <div class="row" id="secondRow">
                    <div class="col">
                        <div class="jumbotron text-center" id="friendsJumbotron">
                            <h1 class="text-center" id="h1Fiends">Friends:</h1>
                            <div id="friendsList">
                                <c:forEach items="${user.friends}" var="friend">
                                    <a href="profile?id=${friend.id}">${friend.username}</a>,
                                </c:forEach>
                            </div>
                            <div id="divAddFriend">
                                <c:if test="${not friend}">
                                    <button class="btn btn-primary text-center border rounded btn-color" type="button" id="addFriend">Add friend</button>
                                    <form id="formAddFriend" method="post" action="changeProfileDetails?change=2">
                                        <div id="insideForm">
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.html" />
    </c:if>
</body>

</html>