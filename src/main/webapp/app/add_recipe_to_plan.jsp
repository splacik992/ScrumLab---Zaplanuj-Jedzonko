<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../dashboardHeader.jsp" %>
        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                    </div>
                    <form method="post" action="/app/recipe/plan/add">
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <input class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" type="submit" value="Zapisz">
                        </div>
                </div>

                <div class="schedules-content">
                    <%--                    <form>--%>
                    <div class="form-group row">
                        <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                            Wybierz plan
                        </label>
                        <div class="col-sm-3">
                            <select class="form-control" id="choosePlan" name="userPlan">
                                <c:forEach var="userPlan" items="${userPlans}">
                                    <option>${userPlan.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-sm-2 label-size col-form-label">
                            Nazwa posiłku
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" value="" id="name" placeholder="Nazwa posiłku"
                                   name = "mealName">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="number" class="col-sm-2 label-size col-form-label">
                            Numer posiłku
                        </label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" value="" id="number" placeholder="Numer posiłku"
                                   name="displayOrder">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="recipie" class="col-sm-2 label-size col-form-label">
                            Przepis
                        </label>
                        <div class="col-sm-4">
                            <select class="form-control" id="recipie", name="userRecipe">
                                <c:forEach var="userRecipe" items="${userRecipes}">
                                    <option>${userRecipe.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="day" class="col-sm-2 label-size col-form-label">
                            Dzień
                        </label>
                        <div class="col-sm-2">
                            <select class="form-control" id="day" name="dayName">
                                <option>poniedziałek</option>
                                <option>wtorek</option>
                                <option>środa</option>
                                <option>czwartek</option>
                                <option>piątek</option>
                                <option>sobota</option>
                                <option>niedziela</option>
                            </select>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
